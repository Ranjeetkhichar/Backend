package com.example.paymentrouting.service;

import com.example.paymentrouting.config.GatewayRoutingProperties;
import com.example.paymentrouting.domain.GatewayName;
import com.example.paymentrouting.domain.PaymentTransaction;
import com.example.paymentrouting.dto.InitiatePaymentRequest;
import com.example.paymentrouting.dto.PaymentResponse;
import com.example.paymentrouting.exception.GatewayCommunicationException;
import com.example.paymentrouting.exception.NoHealthyGatewayException;
import com.example.paymentrouting.exception.ResourceNotFoundException;
import com.example.paymentrouting.gateway.GatewayInitiationResult;
import com.example.paymentrouting.gateway.GatewayPaymentCommand;
import com.example.paymentrouting.gateway.GatewayRegistry;
import com.example.paymentrouting.gateway.PaymentGateway;
import com.example.paymentrouting.repository.PaymentTransactionRepository;
import com.example.paymentrouting.routing.GatewayRouter;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

@Service
public class PaymentService {

    private final PaymentTransactionRepository paymentRepository;
    private final GatewayRouter gatewayRouter;
    private final GatewayRegistry gatewayRegistry;
    private final GatewayHealthService healthService;
    private final GatewayRoutingProperties routingProperties;
    private final MockWebhookSimulator webhookSimulator;

    public PaymentService(
            PaymentTransactionRepository paymentRepository,
            GatewayRouter gatewayRouter,
            GatewayRegistry gatewayRegistry,
            GatewayHealthService healthService,
            GatewayRoutingProperties routingProperties,
            MockWebhookSimulator webhookSimulator
    ) {
        this.paymentRepository = paymentRepository;
        this.gatewayRouter = gatewayRouter;
        this.gatewayRegistry = gatewayRegistry;
        this.healthService = healthService;
        this.routingProperties = routingProperties;
        this.webhookSimulator = webhookSimulator;
    }

    public PaymentResponse initiate(InitiatePaymentRequest request) {
        String idempotencyKey = getIdempotencyKey(request);

        PaymentTransaction existingPayment = paymentRepository
                .findByIdempotencyKey(idempotencyKey)
                .orElse(null);

        if (existingPayment != null) {
            return PaymentResponse.from(existingPayment);
        }

        PaymentTransaction payment = createPayment(request, idempotencyKey);

        try {
            payment = paymentRepository.save(payment);
        } catch (DataIntegrityViolationException exception) {
            return paymentRepository.findByIdempotencyKey(idempotencyKey)
                    .map(PaymentResponse::from)
                    .orElseThrow(() -> exception);
        }

        return routePayment(payment);
    }

    public List<PaymentResponse> getAll() {
        return paymentRepository.findAll().stream()
                .map(PaymentResponse::from)
                .toList();
    }

    public PaymentResponse getById(String transactionId) {
        PaymentTransaction payment = paymentRepository.findById(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Payment transaction not found: " + transactionId
                ));

        return PaymentResponse.from(payment);
    }

    private PaymentResponse routePayment(PaymentTransaction payment) {
        Set<GatewayName> attemptedGateways = EnumSet.noneOf(GatewayName.class);
        int maxAttempts = Math.min(routingProperties.getMaxAttempts(), gatewayRegistry.size());
        String lastError = "No payment gateway was attempted";

        for (int attempt = 0; attempt < maxAttempts; attempt++) {
            GatewayName gatewayName;

            try {
                gatewayName = gatewayRouter.select(attemptedGateways);
            } catch (NoHealthyGatewayException exception) {
                lastError = exception.getMessage();
                break;
            }

            attemptedGateways.add(gatewayName);
            payment.recordRoutingAttempt(gatewayName);
            payment = paymentRepository.save(payment);

            try {
                PaymentGateway gateway = gatewayRegistry.get(gatewayName);
                GatewayInitiationResult result = gateway.initiate(toGatewayCommand(payment));

                if (!result.accepted()) {
                    lastError = result.message();
                    healthService.recordFailure(gatewayName);
                    continue;
                }

                healthService.recordSuccess(gatewayName);
                payment.markPending(result.gatewayPaymentId());
                payment = paymentRepository.save(payment);
                webhookSimulator.schedule(payment);

                return PaymentResponse.from(payment);
            } catch (GatewayCommunicationException exception) {
                lastError = exception.getMessage();
                healthService.recordFailure(gatewayName);
            }
        }

        payment.markFailed("Payment initiation failed: " + lastError);
        return PaymentResponse.from(paymentRepository.save(payment));
    }

    private PaymentTransaction createPayment(
            InitiatePaymentRequest request,
            String idempotencyKey
    ) {
        return PaymentTransaction.create(
                request.orderId().trim(),
                idempotencyKey,
                request.amount(),
                request.currency().trim(),
                request.customerId()
        );
    }

    private String getIdempotencyKey(InitiatePaymentRequest request) {
        if (StringUtils.hasText(request.idempotencyKey())) {
            return request.idempotencyKey().trim();
        }
        return request.orderId().trim();
    }

    private GatewayPaymentCommand toGatewayCommand(PaymentTransaction payment) {
        return new GatewayPaymentCommand(
                payment.getId(),
                payment.getOrderId(),
                payment.getAmount(),
                payment.getCurrency(),
                payment.getCustomerId()
        );
    }
}
