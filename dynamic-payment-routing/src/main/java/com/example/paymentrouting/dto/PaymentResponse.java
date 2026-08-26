package com.example.paymentrouting.dto;

import com.example.paymentrouting.domain.GatewayName;
import com.example.paymentrouting.domain.PaymentStatus;
import com.example.paymentrouting.domain.PaymentTransaction;

import java.math.BigDecimal;
import java.time.Instant;

public record PaymentResponse(
        String transactionId,
        String orderId,
        BigDecimal amount,
        String currency,
        GatewayName gateway,
        String gatewayPaymentId,
        PaymentStatus status,
        int routingAttempts,
        String failureReason,
        Instant createdAt,
        Instant updatedAt
) {
    public static PaymentResponse from(PaymentTransaction payment) {
        return new PaymentResponse(
                payment.getId(),
                payment.getOrderId(),
                payment.getAmount(),
                payment.getCurrency(),
                payment.getGateway(),
                payment.getGatewayPaymentId(),
                payment.getStatus(),
                payment.getRoutingAttempts(),
                payment.getFailureReason(),
                payment.getCreatedAt(),
                payment.getUpdatedAt()
        );
    }
}
