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

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {
        "payment.mock-webhook.enabled=true",
        "payment.mock-webhook.min-delay=50ms",
        "payment.mock-webhook.max-delay=80ms",
        "payment.mock-webhook.success-rate=1.0",
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
class AutomaticWebhookIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void automaticallyUpdatesPendingPaymentToSuccess() throws Exception {
        String responseBody = mockMvc.perform(post("/api/v1/payments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "orderId": "ORDER-AUTO-1",
                                  "amount": 999.00,
                                  "currency": "INR",
                                  "customerId": "CUSTOMER-2",
                                  "idempotencyKey": "AUTO-IDEMPOTENCY-1"
                                }
                                """))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String transactionId = objectMapper.readTree(responseBody)
                .get("transactionId")
                .asText();

        String finalStatus = waitForFinalStatus(transactionId);
        assertThat(finalStatus).isEqualTo("SUCCESS");
    }

    private String waitForFinalStatus(String transactionId) throws Exception {
        String currentStatus = "PENDING";

        for (int attempt = 0; attempt < 30 && currentStatus.equals("PENDING"); attempt++) {
            Thread.sleep(50);

            String responseBody = mockMvc.perform(get("/api/v1/payments/{transactionId}", transactionId))
                    .andExpect(status().isOk())
                    .andReturn()
                    .getResponse()
                    .getContentAsString();

            JsonNode response = objectMapper.readTree(responseBody);
            currentStatus = response.get("status").asText();
        }

        return currentStatus;
    }
}
