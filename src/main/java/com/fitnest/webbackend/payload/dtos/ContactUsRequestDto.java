package com.fitnest.webbackend.payload.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ContactUsRequestDto {
    @NotNull(message = "Name is required")
    private String name;
    @Email(message = "Invalid email address")
    private String email;
    @NotNull(message = "Phone number is required")
    private String phone;
    @NotNull(message = "Message is required")
    private String message;
}
