package com.example.paymentrouting.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record InitiatePaymentRequest(
        @NotBlank String orderId,
        @NotNull @DecimalMin(value = "1.00") BigDecimal amount,
        @NotBlank @Size(min = 3, max = 3) String currency,
        String customerId,
        String idempotencyKey
) {
}
