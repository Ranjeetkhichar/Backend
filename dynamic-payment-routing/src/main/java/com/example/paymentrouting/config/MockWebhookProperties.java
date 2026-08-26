package com.example.paymentrouting.config;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.time.Duration;

@Validated
@ConfigurationProperties(prefix = "payment.mock-webhook")
public class MockWebhookProperties {

    private boolean enabled = true;

    @NotNull
    private Duration minDelay = Duration.ofSeconds(2);

    @NotNull
    private Duration maxDelay = Duration.ofSeconds(5);

    @DecimalMin("0.0")
    @DecimalMax("1.0")
    private double successRate = 0.85;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Duration getMinDelay() {
        return minDelay;
    }

    public void setMinDelay(Duration minDelay) {
        this.minDelay = minDelay;
    }

    public Duration getMaxDelay() {
        return maxDelay;
    }

    public void setMaxDelay(Duration maxDelay) {
        this.maxDelay = maxDelay;
    }

    public double getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(double successRate) {
        this.successRate = successRate;
    }
}
