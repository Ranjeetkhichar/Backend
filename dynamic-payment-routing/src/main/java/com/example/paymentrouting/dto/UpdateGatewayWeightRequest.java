package com.example.paymentrouting.dto;

import jakarta.validation.constraints.Min;

public record UpdateGatewayWeightRequest(@Min(0) int weight) {
}
