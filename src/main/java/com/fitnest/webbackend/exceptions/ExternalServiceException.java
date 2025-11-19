package com.fitnest.webbackend.exceptions;

// 503 External Service Failure
public class ExternalServiceException extends AppException {
    public ExternalServiceException(String serviceName, String reason) {
        super(String.format("Failed to call %s service: %s", serviceName, reason), "ERR_EXTERNAL_SERVICE");
    }
}

