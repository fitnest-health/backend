package com.fitnest.webbackend.business.concretes;

import com.fitnest.webbackend.business.abstracts.CoachService;
import com.fitnest.webbackend.exceptions.ConflictException;
import com.fitnest.webbackend.exceptions.ResourceNotFoundException;
import com.fitnest.webbackend.model.entities.Coach;
import com.fitnest.webbackend.model.entities.Specialty;
import com.fitnest.webbackend.payload.dtos.coach.CreateCoachRequest;
import com.fitnest.webbackend.payload.dtos.coach.CoachResponse;
import com.fitnest.webbackend.payload.dtos.coach.UpdateCoachRequest;
import com.fitnest.webbackend.payload.mappers.CoachMapper;
import com.fitnest.webbackend.repositories.CoachRepository;
import com.fitnest.webbackend.repositories.SpecialtyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class CoachServiceImpl implements CoachService {

    private final CoachRepository coachRepository;
    private final SpecialtyRepository specialtyRepository;
    private final CoachMapper coachMapper;

    @Override
    @Transactional
    public CoachResponse createCoach(CreateCoachRequest request) {
        log.info("ActionLog.createCoach().start : email={}", request.getEmail());

        if (coachRepository.existsByEmail(request.getEmail())) {
            throw new ConflictException("Coach with email " + request.getEmail() + " already exists");
        }

        Coach coach = coachMapper.toEntity(request);

        if (request.getSpecialtyIds() != null && !request.getSpecialtyIds().isEmpty()) {
            Set<Specialty> specialties = specialtyRepository.findAllByIdIn(request.getSpecialtyIds());
            
            if (specialties.size() != request.getSpecialtyIds().size()) {
                Set<Long> foundIds = specialties.stream()
                        .map(Specialty::getId)
                        .collect(java.util.stream.Collectors.toSet());
                Set<Long> missingIds = new HashSet<>(request.getSpecialtyIds());
                missingIds.removeAll(foundIds);
                throw new ResourceNotFoundException("Specialty", "id", missingIds);
            }
            
            coach.setSpecialties(specialties);
        }

        Coach savedCoach = coachRepository.save(coach);
        log.info("ActionLog.createCoach().end : coachId={}", savedCoach.getId());

        return coachMapper.toResponse(savedCoach);
    }

    @Override
    @Transactional
    public CoachResponse updateCoach(Long id, UpdateCoachRequest request) {
        log.info("ActionLog.updateCoach().start : coachId={}", id);

        Coach coach = coachRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Coach", "id", id));

        if (request.getEmail() != null && !request.getEmail().equals(coach.getEmail())) {
            if (coachRepository.existsByEmail(request.getEmail())) {
                throw new ConflictException("Coach with email " + request.getEmail() + " already exists");
            }
            coach.setEmail(request.getEmail());
        }

        if (request.getFullName() != null) {
            coach.setFullName(request.getFullName());
        }
        if (request.getTitle() != null) {
            coach.setTitle(request.getTitle());
        }
        if (request.getBio() != null) {
            coach.setBio(request.getBio());
        }
        if (request.getImageUrl() != null) {
            coach.setImageUrl(request.getImageUrl());
        }
        if (request.getGender() != null) {
            coach.setGender(request.getGender());
        }
        if (request.getPhoneNumber() != null) {
            coach.setPhoneNumber(request.getPhoneNumber());
        }
        if (request.getAddress() != null) {
            coach.setAddress(request.getAddress());
        }
        if (request.getWorkingHoursWeekdays() != null) {
            coach.setWorkingHoursWeekdays(request.getWorkingHoursWeekdays());
        }
        if (request.getWorkingHoursWeekend() != null) {
            coach.setWorkingHoursWeekend(request.getWorkingHoursWeekend());
        }
        if (request.getInstagramUrl() != null) {
            coach.setInstagramUrl(request.getInstagramUrl());
        }
        if (request.getFacebookUrl() != null) {
            coach.setFacebookUrl(request.getFacebookUrl());
        }
        if (request.getLinkedinUrl() != null) {
            coach.setLinkedinUrl(request.getLinkedinUrl());
        }
        if (request.getGymName() != null) {
            coach.setGymName(request.getGymName());
        }

        if (request.getSpecialtyIds() != null) {
            if (request.getSpecialtyIds().isEmpty()) {
                coach.setSpecialties(new HashSet<>());
            } else {
                Set<Specialty> specialties = specialtyRepository.findAllByIdIn(request.getSpecialtyIds());
                
                if (specialties.size() != request.getSpecialtyIds().size()) {
                    Set<Long> foundIds = specialties.stream()
                            .map(Specialty::getId)
                            .collect(java.util.stream.Collectors.toSet());
                    Set<Long> missingIds = new HashSet<>(request.getSpecialtyIds());
                    missingIds.removeAll(foundIds);
                    throw new ResourceNotFoundException("Specialty", "id", missingIds);
                }
                
                coach.setSpecialties(specialties);
            }
        }

        Coach updatedCoach = coachRepository.save(coach);
        log.info("ActionLog.updateCoach().end : coachId={}", updatedCoach.getId());

        return coachMapper.toResponse(updatedCoach);
    }

    @Override
    @Transactional
    public void deleteCoach(Long id) {
        log.info("ActionLog.deleteCoach().start : coachId={}", id);

        Coach coach = coachRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Coach", "id", id));

        coachRepository.delete(coach);
        log.info("ActionLog.deleteCoach().end : coachId={}", id);
    }
}

