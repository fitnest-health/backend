package com.fitnest.webbackend.business.abstracts;

import com.fitnest.webbackend.payload.dtos.specialty.CreateSpecialtyRequest;
import com.fitnest.webbackend.payload.dtos.specialty.SpecialtyResponse;

public interface SpecialtyService {
    SpecialtyResponse createSpecialty(CreateSpecialtyRequest request);
}

