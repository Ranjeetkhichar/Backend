package com.example.paymentrouting.dto;

import com.example.paymentrouting.domain.GatewayName;
import com.example.paymentrouting.domain.GatewayStatus;

import java.time.Instant;

public record GatewayHealthResponse(
        GatewayName gateway,
        int weight,
        GatewayStatus status,
        int consecutiveFailures,
        Instant unhealthyUntil,
        Instant updatedAt
) {
}
