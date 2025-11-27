package com.fitnest.webbackend.payload.dtos.specialty;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateSpecialtyRequest {
    @NotBlank(message = "Name is required")
    private String name;

    private String iconUrl;
}

