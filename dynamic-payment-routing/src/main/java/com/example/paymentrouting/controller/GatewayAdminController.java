package com.example.paymentrouting.controller;

import com.example.paymentrouting.domain.GatewayName;
import com.example.paymentrouting.dto.GatewayHealthResponse;
import com.example.paymentrouting.dto.UpdateGatewayWeightRequest;
import com.example.paymentrouting.service.GatewayHealthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/gateways")
public class GatewayAdminController {

    private final GatewayHealthService healthService;

    public GatewayAdminController(GatewayHealthService healthService) {
        this.healthService = healthService;
    }

    @GetMapping("/health")
    public List<GatewayHealthResponse> getGatewayHealth() {
        return healthService.getAll();
    }

    @GetMapping("/{gateway}/health")
    public GatewayHealthResponse getGatewayHealthByName(@PathVariable GatewayName gateway) {
        return healthService.getByGateway(gateway);
    }

    @PostMapping("/{gateway}/reset-health")
    public GatewayHealthResponse resetGatewayHealth(@PathVariable GatewayName gateway) {
        return healthService.resetHealth(gateway);
    }

    @PatchMapping("/{gateway}/weight")
    public GatewayHealthResponse updateWeight(
            @PathVariable GatewayName gateway,
            @Valid @RequestBody UpdateGatewayWeightRequest request
    ) {
        return healthService.updateWeight(gateway, request.weight());
    }
}
