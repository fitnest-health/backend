package com.fitnest.webbackend.business.abstracts;

import com.fitnest.webbackend.payload.dtos.coach.CreateCoachRequest;
import com.fitnest.webbackend.payload.dtos.coach.CoachResponse;
import com.fitnest.webbackend.payload.dtos.coach.UpdateCoachRequest;

public interface CoachService {
    CoachResponse createCoach(CreateCoachRequest request);
    CoachResponse updateCoach(Long id, UpdateCoachRequest request);
    void deleteCoach(Long id);
}

