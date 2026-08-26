package com.example.paymentrouting.routing;

import com.example.paymentrouting.domain.GatewayName;

import java.util.Set;

public interface GatewayRouter {

    GatewayName select(Set<GatewayName> excludedGateways);
}
