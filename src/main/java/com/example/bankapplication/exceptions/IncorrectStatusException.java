package com.example.bankapplication.exceptions;

public class IncorrectStatusException extends RuntimeException{
    public IncorrectStatusException(String message) {
        super(message);
    }
}
