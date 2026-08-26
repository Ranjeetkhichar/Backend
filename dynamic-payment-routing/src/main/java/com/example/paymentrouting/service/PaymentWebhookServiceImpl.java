package com.example.paymentrouting.service;

import com.example.paymentrouting.domain.GatewayName;
import com.example.paymentrouting.domain.PaymentStatus;
import com.example.paymentrouting.domain.PaymentTransaction;
import com.example.paymentrouting.dto.PaymentResponse;
import com.example.paymentrouting.dto.PaymentWebhookRequest;
import com.example.paymentrouting.exception.InvalidPaymentStateException;
import com.example.paymentrouting.exception.ResourceNotFoundException;
import com.example.paymentrouting.repository.PaymentTransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentWebhookServiceImpl implements PaymentWebhookService {

    private final PaymentTransactionRepository paymentRepository;

    public PaymentWebhookServiceImpl(PaymentTransactionRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    @Transactional
    public PaymentResponse process(GatewayName gateway, PaymentWebhookRequest request) {
        PaymentTransaction payment = paymentRepository.findByIdForUpdate(request.transactionId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Payment transaction not found: " + request.transactionId()
                ));

        validateWebhook(payment, gateway, request);
        payment.applyWebhook(request.status(), request.failureReason());

        return PaymentResponse.from(paymentRepository.save(payment));
    }

    private void validateWebhook(
            PaymentTransaction payment,
            GatewayName gateway,
            PaymentWebhookRequest request
    ) {
        if (payment.getGateway() != gateway) {
            throw new InvalidPaymentStateException("Webhook gateway does not match payment gateway");
        }

        if (!request.gatewayPaymentId().equals(payment.getGatewayPaymentId())) {
            throw new InvalidPaymentStateException("Invalid gateway payment ID");
        }

        if (request.status() != PaymentStatus.SUCCESS
                && request.status() != PaymentStatus.FAILED) {
            throw new InvalidPaymentStateException("Webhook status must be SUCCESS or FAILED");
        }
    }
}
