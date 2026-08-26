package com.example.paymentrouting.domain;

public enum PaymentStatus {
    CREATED,
    PENDING,
    SUCCESS,
    FAILED;

    public boolean isTerminal() {
        return this == SUCCESS || this == FAILED;
    }

    public boolean canTransitionTo(PaymentStatus next) {
        if (this == next) {
            return true;
        }
        return switch (this) {
            case CREATED -> next == PENDING || next == FAILED;
            case PENDING -> next == SUCCESS || next == FAILED;
            case SUCCESS, FAILED -> false;
        };
    }
}
