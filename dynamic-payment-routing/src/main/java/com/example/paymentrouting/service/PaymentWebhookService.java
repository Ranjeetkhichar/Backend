package com.example.paymentrouting.service;

import com.example.paymentrouting.domain.GatewayName;
import com.example.paymentrouting.dto.PaymentResponse;
import com.example.paymentrouting.dto.PaymentWebhookRequest;

public interface PaymentWebhookService {

    PaymentResponse process(GatewayName gateway, PaymentWebhookRequest request);
}
