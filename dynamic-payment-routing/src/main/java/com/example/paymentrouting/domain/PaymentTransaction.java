package com.example.paymentrouting.domain;

import com.example.paymentrouting.exception.InvalidPaymentStateException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "payment_transactions")
public class PaymentTransaction {

    @Id
    private String id;

    @Column(nullable = false)
    private String orderId;

    @Column(nullable = false, unique = true)
    private String idempotencyKey;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false, length = 3)
    private String currency;

    private String customerId;

    @Enumerated(EnumType.STRING)
    private GatewayName gateway;

    private String gatewayPaymentId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status;

    private int routingAttempts;

    @Column(length = 1_000)
    private String failureReason;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant updatedAt;

    @Version
    private Long version;

    protected PaymentTransaction() {
    }

    public static PaymentTransaction create(
            String orderId,
            String idempotencyKey,
            BigDecimal amount,
            String currency,
            String customerId
    ) {
        PaymentTransaction transaction = new PaymentTransaction();
        transaction.id = UUID.randomUUID().toString();
        transaction.orderId = orderId;
        transaction.idempotencyKey = idempotencyKey;
        transaction.amount = amount;
        transaction.currency = currency.toUpperCase();
        transaction.customerId = customerId;
        transaction.status = PaymentStatus.CREATED;
        transaction.createdAt = Instant.now();
        transaction.updatedAt = transaction.createdAt;
        return transaction;
    }

    public void recordRoutingAttempt(GatewayName gateway) {
        this.gateway = gateway;
        this.routingAttempts++;
        touch();
    }

    public void markPending(String gatewayPaymentId) {
        changeStatus(PaymentStatus.PENDING, null);
        this.gatewayPaymentId = gatewayPaymentId;
        touch();
    }

    public void markFailed(String reason) {
        changeStatus(PaymentStatus.FAILED, reason);
    }

    public void applyWebhook(PaymentStatus newStatus, String reason) {
        if (newStatus != PaymentStatus.SUCCESS && newStatus != PaymentStatus.FAILED) {
            throw new InvalidPaymentStateException("Webhook status must be SUCCESS or FAILED");
        }
        changeStatus(newStatus, reason);
    }

    private void changeStatus(PaymentStatus newStatus, String reason) {
        if (!status.canTransitionTo(newStatus)) {
            throw new InvalidPaymentStateException(
                    "Cannot change payment status from " + status + " to " + newStatus
            );
        }
        this.status = newStatus;
        this.failureReason = newStatus == PaymentStatus.FAILED ? reason : null;
        touch();
    }

    private void touch() {
        this.updatedAt = Instant.now();
    }

    public String getId() {
        return id;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCustomerId() {
        return customerId;
    }

    public GatewayName getGateway() {
        return gateway;
    }

    public String getGatewayPaymentId() {
        return gatewayPaymentId;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public int getRoutingAttempts() {
        return routingAttempts;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
