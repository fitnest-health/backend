package com.fitnest.webbackend.payload.dtos.auth;

import com.fitnest.webbackend.model.enums.AppRole;

public record ProfileResponse(
        String email,
        String firstName,
        String lastName,
        AppRole role
) {
}

