package com.example.paymentrouting.gateway;

import com.example.paymentrouting.domain.GatewayName;

public interface PaymentGateway {

    GatewayName name();

    GatewayInitiationResult initiate(GatewayPaymentCommand command);
}
