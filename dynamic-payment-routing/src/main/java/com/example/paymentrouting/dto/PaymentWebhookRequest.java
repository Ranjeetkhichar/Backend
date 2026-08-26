package com.example.paymentrouting.dto;

import com.example.paymentrouting.domain.PaymentStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PaymentWebhookRequest(
        @NotBlank String transactionId,
        @NotBlank String gatewayPaymentId,
        @NotNull PaymentStatus status,
        String failureReason
) {
}
