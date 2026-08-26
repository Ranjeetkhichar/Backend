package com.example.paymentrouting.controller;

import com.example.paymentrouting.domain.GatewayName;
import com.example.paymentrouting.dto.PaymentResponse;
import com.example.paymentrouting.dto.PaymentWebhookRequest;
import com.example.paymentrouting.service.PaymentWebhookService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payments/webhooks")
public class PaymentWebhookController {

    private final PaymentWebhookService webhookService;

    public PaymentWebhookController(PaymentWebhookService webhookService) {
        this.webhookService = webhookService;
    }

    @PostMapping("/{gateway}")
    public PaymentResponse receiveStatusUpdate(
            @PathVariable GatewayName gateway,
            @Valid @RequestBody PaymentWebhookRequest request
    ) {
        return webhookService.process(gateway, request);
    }
}
