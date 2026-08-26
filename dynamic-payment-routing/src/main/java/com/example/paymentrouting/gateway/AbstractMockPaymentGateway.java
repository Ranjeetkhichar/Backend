package com.example.paymentrouting.gateway;

import com.example.paymentrouting.config.GatewayRoutingProperties;
import com.example.paymentrouting.domain.GatewayName;
import com.example.paymentrouting.exception.GatewayCommunicationException;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public abstract class AbstractMockPaymentGateway implements PaymentGateway {

    private final GatewayName gatewayName;
    private final GatewayRoutingProperties properties;

    protected AbstractMockPaymentGateway(
            GatewayName gatewayName,
            GatewayRoutingProperties properties
    ) {
        this.gatewayName = gatewayName;
        this.properties = properties;
    }

    @Override
    public GatewayName name() {
        return gatewayName;
    }

    @Override
    public GatewayInitiationResult initiate(GatewayPaymentCommand command) {
        GatewayRoutingProperties.Gateway config = properties.getGateway(gatewayName);
        simulateLatency(config);

        if (ThreadLocalRandom.current().nextDouble() > config.getSuccessRate()) {
            throw new GatewayCommunicationException(gatewayName + " is temporarily unavailable");
        }

        String gatewayPaymentId = gatewayName.name().toLowerCase()
                + "_pay_"
                + UUID.randomUUID().toString().replace("-", "");
        return GatewayInitiationResult.accepted(gatewayPaymentId);
    }

    private void simulateLatency(GatewayRoutingProperties.Gateway config) {
        int min = Math.min(config.getMinDelayMs(), config.getMaxDelayMs());
        int max = Math.max(config.getMinDelayMs(), config.getMaxDelayMs());
        int delay = min == max ? min : ThreadLocalRandom.current().nextInt(min, max + 1);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            throw new GatewayCommunicationException(gatewayName + " request was interrupted", exception);
        }
    }
}
