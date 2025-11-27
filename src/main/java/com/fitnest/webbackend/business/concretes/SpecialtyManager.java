package com.fitnest.webbackend.business.concretes;

import com.fitnest.webbackend.business.abstracts.SpecialtyService;
import com.fitnest.webbackend.model.entities.Specialty;
import com.fitnest.webbackend.payload.dtos.specialty.CreateSpecialtyRequest;
import com.fitnest.webbackend.payload.dtos.specialty.SpecialtyResponse;
import com.fitnest.webbackend.payload.mappers.SpecialtyMapper;
import com.fitnest.webbackend.repositories.SpecialtyRepository;
import com.fitnest.webbackend.validation.SpecialtyValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SpecialtyManager implements SpecialtyService {

    private final SpecialtyRepository specialtyRepository;
    private final SpecialtyMapper specialtyMapper;
    private final SpecialtyValidator specialtyValidator;

    @Override
    @Transactional
    public SpecialtyResponse createSpecialty(CreateSpecialtyRequest request) {
        log.info("ActionLog.createSpecialty().start : name={}", request.getName());

        specialtyValidator.validateNameUniqueness(request.getName());

        Specialty specialty = specialtyMapper.toEntity(request);
        Specialty savedSpecialty = specialtyRepository.save(specialty);
        log.info("ActionLog.createSpecialty().end : specialtyId={}", savedSpecialty.getId());

        return specialtyMapper.toResponse(savedSpecialty);
    }
}

