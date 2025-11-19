package com.fitnest.webbackend.exceptions;

public class BadRequestException extends AppException {
    public BadRequestException(String message) {
        super(message, "ERR_BAD_REQUEST");
    }
}
