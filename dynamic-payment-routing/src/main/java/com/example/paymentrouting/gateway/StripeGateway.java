package com.example.paymentrouting.gateway;

import com.example.paymentrouting.config.GatewayRoutingProperties;
import com.example.paymentrouting.domain.GatewayName;
import org.springframework.stereotype.Component;

@Component
public class StripeGateway extends AbstractMockPaymentGateway {

    public StripeGateway(GatewayRoutingProperties properties) {
        super(GatewayName.STRIPE, properties);
    }
}
