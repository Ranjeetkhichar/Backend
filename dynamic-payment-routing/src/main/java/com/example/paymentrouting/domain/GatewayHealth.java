package com.example.paymentrouting.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

import java.time.Duration;
import java.time.Instant;

@Entity
@Table(name = "gateway_health")
public class GatewayHealth {

    @Id
    @Enumerated(EnumType.STRING)
    private GatewayName gateway;

    @Column(nullable = false)
    private int weight;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GatewayStatus status;

    @Column(nullable = false)
    private int consecutiveFailures;

    private Instant unhealthyUntil;

    @Column(nullable = false)
    private Instant updatedAt;

    @Version
    private Long version;

    protected GatewayHealth() {
    }

    public static GatewayHealth create(GatewayName gateway, int weight) {
        GatewayHealth health = new GatewayHealth();
        health.gateway = gateway;
        health.weight = weight;
        health.status = GatewayStatus.HEALTHY;
        health.consecutiveFailures = 0;
        health.updatedAt = Instant.now();
        return health;
    }

    public void markSuccess() {
        this.consecutiveFailures = 0;
        this.status = GatewayStatus.HEALTHY;
        this.unhealthyUntil = null;
        this.updatedAt = Instant.now();
    }

    public void markFailure(int failureThreshold, Duration cooldown) {
        this.consecutiveFailures++;
        if (consecutiveFailures >= failureThreshold) {
            this.status = GatewayStatus.UNHEALTHY;
            this.unhealthyUntil = Instant.now().plus(cooldown);
        }
        this.updatedAt = Instant.now();
    }

    public boolean recoverIfDue(Instant now) {
        if (status == GatewayStatus.UNHEALTHY
                && unhealthyUntil != null
                && !unhealthyUntil.isAfter(now)) {
            markSuccess();
            return true;
        }
        return false;
    }

    public boolean isAvailable() {
        return status == GatewayStatus.HEALTHY && weight > 0;
    }

    public void updateWeight(int weight) {
        this.weight = weight;
        this.updatedAt = Instant.now();
    }

    public GatewayName getGateway() {
        return gateway;
    }

    public int getWeight() {
        return weight;
    }

    public GatewayStatus getStatus() {
        return status;
    }

    public int getConsecutiveFailures() {
        return consecutiveFailures;
    }

    public Instant getUnhealthyUntil() {
        return unhealthyUntil;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
