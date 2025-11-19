package com.fitnest.webbackend.exceptions;

// 403 Forbidden
public class ForbiddenException extends AppException {
    public ForbiddenException(String message) {
        super(message, "ERR_FORBIDDEN");
    }
}