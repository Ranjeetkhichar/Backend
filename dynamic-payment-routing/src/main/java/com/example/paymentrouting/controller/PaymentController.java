package com.example.paymentrouting.controller;

import com.example.paymentrouting.domain.PaymentStatus;
import com.example.paymentrouting.dto.InitiatePaymentRequest;
import com.example.paymentrouting.dto.PaymentResponse;
import com.example.paymentrouting.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<PaymentResponse> initiate(
            @Valid @RequestBody InitiatePaymentRequest request
    ) {
        PaymentResponse response = paymentService.initiate(request);
        HttpStatus status = response.status() == PaymentStatus.FAILED
                ? HttpStatus.SERVICE_UNAVAILABLE
                : HttpStatus.CREATED;
        return ResponseEntity.status(status).body(response);
    }

    @PostMapping("/demo/random")
    public ResponseEntity<PaymentResponse> initiateWithRandomData() {
        String suffix = UUID.randomUUID().toString().substring(0, 8);
        BigDecimal amount = BigDecimal.valueOf(
                ThreadLocalRandom.current().nextLong(100, 10_001)
        );
        InitiatePaymentRequest request = new InitiatePaymentRequest(
                "ORDER-" + suffix,
                amount,
                "INR",
                "CUSTOMER-" + suffix,
                "IDEMPOTENCY-" + suffix
        );
        return initiate(request);
    }

    @GetMapping
    public List<PaymentResponse> getAll() {
        return paymentService.getAll();
    }

    @GetMapping("/{transactionId}")
    public PaymentResponse getById(@PathVariable String transactionId) {
        return paymentService.getById(transactionId);
    }
}
