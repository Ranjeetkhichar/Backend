package com.example.paymentrouting.config;

import com.example.paymentrouting.domain.GatewayName;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Validated
@ConfigurationProperties(prefix = "payment.routing")
public class GatewayRoutingProperties {

    @Min(1)
    private int failureThreshold = 3;

    @NotNull
    private Duration cooldown = Duration.ofSeconds(30);

    @Min(1)
    private int maxAttempts = 3;

    @Min(100)
    private long recoveryScanDelayMs = 5_000;

    @Valid
    @NotEmpty
    private List<Gateway> gateways = new ArrayList<>();

    public Gateway getGateway(GatewayName name) {
        return gateways.stream()
                .filter(gateway -> gateway.getName() == name)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Missing configuration for gateway " + name));
    }

    public int getFailureThreshold() {
        return failureThreshold;
    }

    public void setFailureThreshold(int failureThreshold) {
        this.failureThreshold = failureThreshold;
    }

    public Duration getCooldown() {
        return cooldown;
    }

    public void setCooldown(Duration cooldown) {
        this.cooldown = cooldown;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public void setMaxAttempts(int maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

    public long getRecoveryScanDelayMs() {
        return recoveryScanDelayMs;
    }

    public void setRecoveryScanDelayMs(long recoveryScanDelayMs) {
        this.recoveryScanDelayMs = recoveryScanDelayMs;
    }

    public List<Gateway> getGateways() {
        return gateways;
    }

    public void setGateways(List<Gateway> gateways) {
        this.gateways = gateways;
    }

    public static class Gateway {

        @NotNull
        private GatewayName name;

        @Min(0)
        private int weight;

        @DecimalMin("0.0")
        @DecimalMax("1.0")
        private double successRate = 1.0;

        @Min(0)
        private int minDelayMs = 50;

        @Min(0)
        private int maxDelayMs = 200;

        public GatewayName getName() {
            return name;
        }

        public void setName(GatewayName name) {
            this.name = name;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public double getSuccessRate() {
            return successRate;
        }

        public void setSuccessRate(double successRate) {
            this.successRate = successRate;
        }

        public int getMinDelayMs() {
            return minDelayMs;
        }

        public void setMinDelayMs(int minDelayMs) {
            this.minDelayMs = minDelayMs;
        }

        public int getMaxDelayMs() {
            return maxDelayMs;
        }

        public void setMaxDelayMs(int maxDelayMs) {
            this.maxDelayMs = maxDelayMs;
        }
    }
}
