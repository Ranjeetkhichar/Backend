package com.example.paymentrouting.gateway;

import java.math.BigDecimal;

public record GatewayPaymentCommand(
        String transactionId,
        String orderId,
        BigDecimal amount,
        String currency,
        String customerId
) {
}
