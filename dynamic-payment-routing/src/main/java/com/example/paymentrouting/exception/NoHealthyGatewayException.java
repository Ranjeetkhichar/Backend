package com.example.paymentrouting.exception;

public class NoHealthyGatewayException extends RuntimeException {

    public NoHealthyGatewayException(String message) {
        super(message);
    }
}
