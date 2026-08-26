package com.example.paymentrouting.exception;

public class GatewayCommunicationException extends RuntimeException {

    public GatewayCommunicationException(String message) {
        super(message);
    }

    public GatewayCommunicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
