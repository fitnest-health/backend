package com.fitnest.webbackend.exceptions;

// 404 Not Found
public class ResourceNotFoundException extends AppException {
    public ResourceNotFoundException(String resource, String field, Object value) {
        super(String.format("%s not found with %s: %s", resource, field, value), "ERR_RESOURCE_NOT_FOUND");
    }

    public ResourceNotFoundException(String resource) {
        super(String.format("No %s found", resource), "ERR_RESOURCE_NOT_FOUND");
    }

}