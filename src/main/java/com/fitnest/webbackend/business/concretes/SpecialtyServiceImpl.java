package com.fitnest.webbackend.business.concretes;

import com.fitnest.webbackend.business.abstracts.SpecialtyService;
import com.fitnest.webbackend.exceptions.ConflictException;
import com.fitnest.webbackend.model.entities.Specialty;
import com.fitnest.webbackend.payload.dtos.specialty.CreateSpecialtyRequest;
import com.fitnest.webbackend.payload.dtos.specialty.SpecialtyResponse;
import com.fitnest.webbackend.payload.mappers.SpecialtyMapper;
import com.fitnest.webbackend.repositories.SpecialtyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SpecialtyServiceImpl implements SpecialtyService {

    private final SpecialtyRepository specialtyRepository;
    private final SpecialtyMapper specialtyMapper;

    @Override
    @Transactional
    public SpecialtyResponse createSpecialty(CreateSpecialtyRequest request) {
        log.info("ActionLog.createSpecialty().start : name={}", request.getName());

        if (specialtyRepository.existsByName(request.getName())) {
            throw new ConflictException("Specialty with name " + request.getName() + " already exists");
        }

        Specialty specialty = specialtyMapper.toEntity(request);
        Specialty savedSpecialty = specialtyRepository.save(specialty);
        log.info("ActionLog.createSpecialty().end : specialtyId={}", savedSpecialty.getId());

        return specialtyMapper.toResponse(savedSpecialty);
    }
}

