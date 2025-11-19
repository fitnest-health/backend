package com.fitnest.webbackend.exceptions;

public class ConflictException extends AppException {
    public ConflictException(String message) {
        super(message, "ERR_CONFLICT");
    }
}
