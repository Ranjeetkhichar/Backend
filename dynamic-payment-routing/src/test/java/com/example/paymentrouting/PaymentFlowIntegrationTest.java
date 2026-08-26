package com.example.paymentrouting;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {
        "payment.mock-webhook.enabled=false",
        "payment.routing.gateways[0].name=RAZORPAY",
        "payment.routing.gateways[0].weight=50",
        "payment.routing.gateways[0].success-rate=1.0",
        "payment.routing.gateways[1].name=STRIPE",
        "payment.routing.gateways[1].weight=30",
        "payment.routing.gateways[1].success-rate=1.0",
        "payment.routing.gateways[2].name=CASHFREE",
        "payment.routing.gateways[2].weight=20",
        "payment.routing.gateways[2].success-rate=1.0"
})
class PaymentFlowIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void initiatesPaymentAndProcessesManualWebhook() throws Exception {
        String responseBody = mockMvc.perform(post("/api/v1/payments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "orderId": "ORDER-MANUAL-1",
                                  "amount": 1200.00,
                                  "currency": "INR",
                                  "customerId": "CUSTOMER-1",
                                  "idempotencyKey": "MANUAL-IDEMPOTENCY-1"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value("PENDING"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        JsonNode payment = objectMapper.readTree(responseBody);
        String transactionId = payment.get("transactionId").asText();
        String gateway = payment.get("gateway").asText();
        String gatewayPaymentId = payment.get("gatewayPaymentId").asText();

        String webhookBody = objectMapper.writeValueAsString(new WebhookPayload(
                transactionId,
                gatewayPaymentId,
                "SUCCESS",
                null
        ));

        mockMvc.perform(post("/api/v1/payments/webhooks/{gateway}", gateway)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(webhookBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("SUCCESS"));
    }

    private record WebhookPayload(
            String transactionId,
            String gatewayPaymentId,
            String status,
            String failureReason
    ) {
    }
}
