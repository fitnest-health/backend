package com.fitnest.webbackend.validation;

import com.fitnest.webbackend.exceptions.ConflictException;
import com.fitnest.webbackend.exceptions.ResourceNotFoundException;
import com.fitnest.webbackend.model.entities.Specialty;
import com.fitnest.webbackend.repositories.CoachRepository;
import com.fitnest.webbackend.repositories.SpecialtyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class CoachValidator {

    private final CoachRepository coachRepository;
    private final SpecialtyRepository specialtyRepository;

    public void validateEmailUniqueness(String email) {
        if (coachRepository.existsByEmail(email)) {
            throw new ConflictException("Coach with email " + email + " already exists");
        }
    }

    public void validateEmailUniquenessForUpdate(String email, String currentEmail) {
        if (email != null && !email.equals(currentEmail)) {
            validateEmailUniqueness(email);
        }
    }

    public Set<Specialty> validateAndGetSpecialties(Set<Long> specialtyIds) {
        if (specialtyIds == null || specialtyIds.isEmpty()) {
            return new HashSet<>();
        }

        Set<Specialty> specialties = specialtyRepository.findAllByIdIn(specialtyIds);
        
        if (specialties.size() != specialtyIds.size()) {
            Set<Long> foundIds = specialties.stream()
                    .map(Specialty::getId)
                    .collect(java.util.stream.Collectors.toSet());
            Set<Long> missingIds = new HashSet<>(specialtyIds);
            missingIds.removeAll(foundIds);
            throw new ResourceNotFoundException("Specialty", "id", missingIds);
        }
        
        return specialties;
    }
}

