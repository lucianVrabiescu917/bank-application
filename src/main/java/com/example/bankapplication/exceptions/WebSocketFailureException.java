package com.example.bankapplication.exceptions;

public class WebSocketFailureException extends RuntimeException {
    public WebSocketFailureException(String message) {
        super(message);
    }
}
