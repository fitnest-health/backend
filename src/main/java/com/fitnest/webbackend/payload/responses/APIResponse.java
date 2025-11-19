package com.fitnest.webbackend.payload.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIResponse {
    String message;
    boolean success;
    String resourceName;
    private Map<String, String> errors;

    public APIResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public APIResponse(String message, boolean success, String resourceName) {
        this.message = message;
        this.resourceName = resourceName;
        this.success = success;
    }
}
