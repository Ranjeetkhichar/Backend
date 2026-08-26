package com.example.paymentrouting.routing;

import com.example.paymentrouting.domain.GatewayName;

public record GatewayCandidate(GatewayName gateway, int weight) {
}
