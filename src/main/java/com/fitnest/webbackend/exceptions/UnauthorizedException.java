package com.fitnest.webbackend.exceptions;

// 401 Unauthorized
public class UnauthorizedException extends AppException {
    public UnauthorizedException(String message) {
        super(message, "ERR_UNAUTHORIZED");
    }
}