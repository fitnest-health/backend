package com.fitnest.webbackend.business.concretes;

import com.fitnest.webbackend.business.abstracts.CoachService;
import com.fitnest.webbackend.exceptions.ResourceNotFoundException;
import com.fitnest.webbackend.model.entities.Coach;
import com.fitnest.webbackend.payload.dtos.coach.CreateCoachRequest;
import com.fitnest.webbackend.payload.dtos.coach.CoachResponse;
import com.fitnest.webbackend.payload.dtos.coach.UpdateCoachRequest;
import com.fitnest.webbackend.payload.mappers.CoachMapper;
import com.fitnest.webbackend.repositories.CoachRepository;
import com.fitnest.webbackend.validation.CoachValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.function.Consumer;

@Slf4j
@Service
@RequiredArgsConstructor
public class CoachManager implements CoachService {

    private final CoachRepository coachRepository;
    private final CoachMapper coachMapper;
    private final CoachValidator coachValidator;

    @Override
    @Transactional
    public CoachResponse createCoach(CreateCoachRequest request) {
        log.info("ActionLog.createCoach().start : email={}", request.getEmail());

        coachValidator.validateEmailUniqueness(request.getEmail());

        Coach coach = coachMapper.toEntity(request);
        coach.setSpecialties(coachValidator.validateAndGetSpecialties(request.getSpecialtyIds()));

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

        if (request.getEmail() != null && !Objects.equals(request.getEmail(), coach.getEmail())) {
            coachValidator.validateEmailUniqueness(request.getEmail());
            coach.setEmail(request.getEmail());
        }

        updateIfNotNull(request.getFullName(), coach::setFullName);
        updateIfNotNull(request.getTitle(), coach::setTitle);
        updateIfNotNull(request.getBio(), coach::setBio);
        updateIfNotNull(request.getImageUrl(), coach::setImageUrl);
        updateIfNotNull(request.getGender(), coach::setGender);
        updateIfNotNull(request.getPhoneNumber(), coach::setPhoneNumber);
        updateIfNotNull(request.getAddress(), coach::setAddress);
        updateIfNotNull(request.getWorkingHoursWeekdays(), coach::setWorkingHoursWeekdays);
        updateIfNotNull(request.getWorkingHoursWeekend(), coach::setWorkingHoursWeekend);
        updateIfNotNull(request.getInstagramUrl(), coach::setInstagramUrl);
        updateIfNotNull(request.getFacebookUrl(), coach::setFacebookUrl);
        updateIfNotNull(request.getLinkedinUrl(), coach::setLinkedinUrl);
        updateIfNotNull(request.getGymName(), coach::setGymName);

        if (request.getSpecialtyIds() != null) {
            coach.setSpecialties(coachValidator.validateAndGetSpecialties(request.getSpecialtyIds()));
        }

        Coach updatedCoach = coachRepository.save(coach);
        log.info("ActionLog.updateCoach().end : coachId={}", updatedCoach.getId());

        return coachMapper.toResponse(updatedCoach);
    }

    private <T> void updateIfNotNull(T value, Consumer<T> setter) {
        if (value != null) {
            setter.accept(value);
        }
    }

    @Override
    @Transactional
    public void deleteCoach(Long id) {
        log.info("ActionLog.deleteCoach().start : coachId={}", id);

        if (!coachRepository.existsById(id)) {
            throw new ResourceNotFoundException("Coach", "id", id);
        }

        coachRepository.deleteById(id);
        log.info("ActionLog.deleteCoach().end : coachId={}", id);
    }
}

