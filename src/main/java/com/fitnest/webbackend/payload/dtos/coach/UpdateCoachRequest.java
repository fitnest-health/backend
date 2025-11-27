package com.fitnest.webbackend.payload.dtos.coach;

import com.fitnest.webbackend.model.enums.Gender;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.util.Set;

@Data
public class UpdateCoachRequest {
    private String fullName;
    private String title;
    private String bio;
    private String imageUrl;
    private Gender gender;
    private String phoneNumber;

    @Email(message = "Invalid email address")
    private String email;

    private String address;
    private String workingHoursWeekdays;
    private String workingHoursWeekend;
    private String instagramUrl;
    private String facebookUrl;
    private String linkedinUrl;
    private Set<Long> specialtyIds;
    private String gymName;
}

