package com.fitnest.webbackend.validation;

import com.fitnest.webbackend.exceptions.ConflictException;
import com.fitnest.webbackend.repositories.SpecialtyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpecialtyValidator {

    private final SpecialtyRepository specialtyRepository;

    public void validateNameUniqueness(String name) {
        if (specialtyRepository.existsByName(name)) {
            throw new ConflictException("Specialty with name " + name + " already exists");
        }
    }
}

