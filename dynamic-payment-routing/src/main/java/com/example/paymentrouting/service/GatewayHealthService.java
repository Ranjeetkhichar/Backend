package com.example.paymentrouting.service;

import com.example.paymentrouting.config.GatewayRoutingProperties;
import com.example.paymentrouting.domain.GatewayHealth;
import com.example.paymentrouting.domain.GatewayName;
import com.example.paymentrouting.dto.GatewayHealthResponse;
import com.example.paymentrouting.exception.ResourceNotFoundException;
import com.example.paymentrouting.repository.GatewayHealthRepository;
import com.example.paymentrouting.routing.GatewayCandidate;
import jakarta.annotation.PostConstruct;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Service
public class GatewayHealthService {

    private final GatewayHealthRepository repository;
    private final GatewayRoutingProperties properties;

    public GatewayHealthService(
            GatewayHealthRepository repository,
            GatewayRoutingProperties properties
    ) {
        this.repository = repository;
        this.properties = properties;
    }

    @PostConstruct
    @Transactional
    public void initialize() {
        for (GatewayRoutingProperties.Gateway config : properties.getGateways()) {
            GatewayHealth health = repository.findById(config.getName())
                    .orElseGet(() -> GatewayHealth.create(config.getName(), config.getWeight()));
            health.updateWeight(config.getWeight());
            repository.save(health);
        }
    }

    @Transactional
    public List<GatewayCandidate> getHealthyCandidates(Set<GatewayName> excluded) {
        Instant now = Instant.now();
        List<GatewayHealth> healthRecords = repository.findAll();
        for (GatewayHealth health : healthRecords) {
            health.recoverIfDue(now);
        }
        repository.saveAll(healthRecords);

        return healthRecords.stream()
                .filter(GatewayHealth::isAvailable)
                .filter(health -> !excluded.contains(health.getGateway()))
                .map(health -> new GatewayCandidate(health.getGateway(), health.getWeight()))
                .toList();
    }

    @Transactional
    public void recordSuccess(GatewayName gatewayName) {
        GatewayHealth health = findForUpdate(gatewayName);
        health.markSuccess();
        repository.save(health);
    }

    @Transactional
    public void recordFailure(GatewayName gatewayName) {
        GatewayHealth health = findForUpdate(gatewayName);
        health.markFailure(properties.getFailureThreshold(), properties.getCooldown());
        repository.save(health);
    }

    @Scheduled(fixedDelayString = "${payment.routing.recovery-scan-delay-ms:5000}")
    @Transactional
    public void recoverExpiredGateways() {
        Instant now = Instant.now();
        List<GatewayHealth> healthRecords = repository.findAll();
        boolean changed = false;
        for (GatewayHealth health : healthRecords) {
            changed = health.recoverIfDue(now) || changed;
        }
        if (changed) {
            repository.saveAll(healthRecords);
        }
    }

    @Transactional(readOnly = true)
    public List<GatewayHealthResponse> getAll() {
        return repository.findAll().stream()
                .sorted(Comparator.comparing(GatewayHealth::getGateway))
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public GatewayHealthResponse getByGateway(GatewayName gatewayName) {
        GatewayHealth health = repository.findById(gatewayName)
                .orElseThrow(() -> new ResourceNotFoundException("Gateway not found: " + gatewayName));
        return toResponse(health);
    }

    @Transactional
    public GatewayHealthResponse resetHealth(GatewayName gatewayName) {
        GatewayHealth health = findForUpdate(gatewayName);
        health.markSuccess();
        repository.save(health);
        return toResponse(health);
    }

    @Transactional
    public GatewayHealthResponse updateWeight(GatewayName gatewayName, int weight) {
        GatewayHealth health = findForUpdate(gatewayName);
        health.updateWeight(weight);
        repository.save(health);
        return toResponse(health);
    }

    private GatewayHealth findForUpdate(GatewayName gatewayName) {
        return repository.findByGatewayForUpdate(gatewayName)
                .orElseThrow(() -> new ResourceNotFoundException("Gateway not found: " + gatewayName));
    }

    private GatewayHealthResponse toResponse(GatewayHealth health) {
        return new GatewayHealthResponse(
                health.getGateway(),
                health.getWeight(),
                health.getStatus(),
                health.getConsecutiveFailures(),
                health.getUnhealthyUntil(),
                health.getUpdatedAt()
        );
    }
}
