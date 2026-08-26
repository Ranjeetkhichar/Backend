package com.example.paymentrouting.gateway;

import com.example.paymentrouting.domain.GatewayName;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component
public class GatewayRegistry {

    private final Map<GatewayName, PaymentGateway> gateways;

    public GatewayRegistry(List<PaymentGateway> gatewayClients) {
        Map<GatewayName, PaymentGateway> clients = new EnumMap<>(GatewayName.class);
        for (PaymentGateway client : gatewayClients) {
            PaymentGateway duplicate = clients.put(client.name(), client);
            if (duplicate != null) {
                throw new IllegalStateException("Duplicate gateway client for " + client.name());
            }
        }
        this.gateways = Map.copyOf(clients);
    }

    public PaymentGateway get(GatewayName gatewayName) {
        PaymentGateway gateway = gateways.get(gatewayName);
        if (gateway == null) {
            throw new IllegalStateException("No client registered for gateway " + gatewayName);
        }
        return gateway;
    }

    public int size() {
        return gateways.size();
    }
}
