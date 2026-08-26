package com.example.paymentrouting.service;

import com.example.paymentrouting.config.MockWebhookProperties;
import com.example.paymentrouting.domain.GatewayName;
import com.example.paymentrouting.domain.PaymentStatus;
import com.example.paymentrouting.domain.PaymentTransaction;
import com.example.paymentrouting.dto.PaymentWebhookRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class MockWebhookSimulator {

    private static final Logger log = LoggerFactory.getLogger(MockWebhookSimulator.class);

    private final TaskScheduler taskScheduler;
    private final PaymentWebhookService webhookService;
    private final MockWebhookProperties properties;

    public MockWebhookSimulator(
            TaskScheduler taskScheduler,
            PaymentWebhookService webhookService,
            MockWebhookProperties properties
    ) {
        this.taskScheduler = taskScheduler;
        this.webhookService = webhookService;
        this.properties = properties;
    }

    public void schedule(PaymentTransaction payment) {
        if (!properties.isEnabled()) {
            return;
        }

        String transactionId = payment.getId();
        String gatewayPaymentId = payment.getGatewayPaymentId();
        GatewayName gateway = payment.getGateway();
        Instant executionTime = Instant.now().plusMillis(randomDelayInMillis());

        taskScheduler.schedule(
                () -> sendWebhook(transactionId, gatewayPaymentId, gateway),
                executionTime
        );
    }

    private void sendWebhook(
            String transactionId,
            String gatewayPaymentId,
            GatewayName gateway
    ) {
        PaymentStatus status = randomPaymentStatus();
        String failureReason = status == PaymentStatus.FAILED
                ? "Payment declined by mock gateway"
                : null;

        PaymentWebhookRequest request = new PaymentWebhookRequest(
                transactionId,
                gatewayPaymentId,
                status,
                failureReason
        );

        try {
            webhookService.process(gateway, request);
            log.info("Mock webhook processed for transaction {} with status {}", transactionId, status);
        } catch (RuntimeException exception) {
            log.warn("Mock webhook failed for transaction {}: {}", transactionId, exception.getMessage());
        }
    }

    private PaymentStatus randomPaymentStatus() {
        return ThreadLocalRandom.current().nextDouble() < properties.getSuccessRate()
                ? PaymentStatus.SUCCESS
                : PaymentStatus.FAILED;
    }

    private long randomDelayInMillis() {
        long first = properties.getMinDelay().toMillis();
        long second = properties.getMaxDelay().toMillis();
        long min = Math.min(first, second);
        long max = Math.max(first, second);

        return min == max
                ? min
                : ThreadLocalRandom.current().nextLong(min, max + 1);
    }
}
