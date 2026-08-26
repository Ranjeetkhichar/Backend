package com.example.paymentrouting.gateway;

import com.example.paymentrouting.config.GatewayRoutingProperties;
import com.example.paymentrouting.domain.GatewayName;
import org.springframework.stereotype.Component;

@Component
public class CashfreeGateway extends AbstractMockPaymentGateway {

    public CashfreeGateway(GatewayRoutingProperties properties) {
        super(GatewayName.CASHFREE, properties);
    }
}
