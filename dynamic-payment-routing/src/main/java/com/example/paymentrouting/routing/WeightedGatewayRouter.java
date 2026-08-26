package com.example.paymentrouting.routing;

import com.example.paymentrouting.domain.GatewayName;
import com.example.paymentrouting.exception.NoHealthyGatewayException;
import com.example.paymentrouting.service.GatewayHealthService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class WeightedGatewayRouter implements GatewayRouter {

    private final GatewayHealthService healthService;

    public WeightedGatewayRouter(GatewayHealthService healthService) {
        this.healthService = healthService;
    }

    @Override
    public GatewayName select(Set<GatewayName> excludedGateways) {
        List<GatewayCandidate> candidates = healthService.getHealthyCandidates(excludedGateways);
        if (candidates.isEmpty()) {
            throw new NoHealthyGatewayException("No healthy payment gateway is available");
        }

        int totalWeight = candidates.stream()
                .mapToInt(GatewayCandidate::weight)
                .sum();

        int randomWeight = ThreadLocalRandom.current().nextInt(totalWeight);
        int currentWeight = 0;

        for (GatewayCandidate candidate : candidates) {
            currentWeight += candidate.weight();
            if (randomWeight < currentWeight) {
                return candidate.gateway();
            }
        }

        throw new IllegalStateException("Unable to select a payment gateway");
    }
}
