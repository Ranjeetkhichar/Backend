package com.example.paymentrouting.gateway;

import com.example.paymentrouting.config.GatewayRoutingProperties;
import com.example.paymentrouting.domain.GatewayName;
import org.springframework.stereotype.Component;

@Component
public class RazorpayGateway extends AbstractMockPaymentGateway {

    public RazorpayGateway(GatewayRoutingProperties properties) {
        super(GatewayName.RAZORPAY, properties);
    }
}
