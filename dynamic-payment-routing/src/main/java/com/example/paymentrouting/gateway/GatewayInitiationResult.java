package com.example.paymentrouting.gateway;

public record GatewayInitiationResult(
        boolean accepted,
        String gatewayPaymentId,
        String message
) {
    public static GatewayInitiationResult accepted(String gatewayPaymentId) {
        return new GatewayInitiationResult(true, gatewayPaymentId, "Payment accepted by gateway");
    }

    public static GatewayInitiationResult rejected(String message) {
        return new GatewayInitiationResult(false, null, message);
    }
}
