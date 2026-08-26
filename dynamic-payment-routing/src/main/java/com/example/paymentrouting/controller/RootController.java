package com.example.paymentrouting.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class RootController {

    @GetMapping("/")
    public Map<String, Object> root() {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("service", "Dynamic Payment Gateway Routing Service");
        response.put("status", "UP");

        Map<String, String> endpoints = new LinkedHashMap<>();
        endpoints.put("initiatePayment", "POST /api/v1/payments");
        endpoints.put("initiateDemoPayment", "POST /api/v1/payments/demo/random");
        endpoints.put("getAllPayments", "GET /api/v1/payments");
        endpoints.put("getPaymentById", "GET /api/v1/payments/{transactionId}");
        endpoints.put("receiveWebhook", "POST /api/v1/payments/webhooks/{gateway}");
        endpoints.put("getGatewayHealth", "GET /api/v1/gateways/health");
        endpoints.put("getGatewayHealthByName", "GET /api/v1/gateways/{gateway}/health");
        endpoints.put("resetGatewayHealth", "POST /api/v1/gateways/{gateway}/reset-health");
        endpoints.put("updateGatewayWeight", "PATCH /api/v1/gateways/{gateway}/weight");
        endpoints.put("h2Console", "GET /h2-console");

        response.put("endpoints", endpoints);
        return response;
    }
}
