package com.fitnest.webbackend.payload.dtos.coach;

import com.fitnest.webbackend.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoachResponse {
    private Long id;
    private String fullName;
    private String title;
    private String bio;
    private String imageUrl;
    private Gender gender;
    private String phoneNumber;
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

