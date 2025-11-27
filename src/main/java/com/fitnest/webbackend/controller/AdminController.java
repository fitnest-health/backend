package com.fitnest.webbackend.controller;

import com.fitnest.webbackend.business.abstracts.CoachService;
import com.fitnest.webbackend.business.abstracts.SpecialtyService;
import com.fitnest.webbackend.payload.dtos.coach.CreateCoachRequest;
import com.fitnest.webbackend.payload.dtos.coach.CoachResponse;
import com.fitnest.webbackend.payload.dtos.coach.UpdateCoachRequest;
import com.fitnest.webbackend.payload.dtos.specialty.CreateSpecialtyRequest;
import com.fitnest.webbackend.payload.dtos.specialty.SpecialtyResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final CoachService coachService;
    private final SpecialtyService specialtyService;

    @PostMapping("/coaches")
    public ResponseEntity<CoachResponse> createCoach(@Valid @RequestBody CreateCoachRequest request) {
        CoachResponse response = coachService.createCoach(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/coaches/{id}")
    public ResponseEntity<CoachResponse> updateCoach(
            @PathVariable Long id,
            @Valid @RequestBody UpdateCoachRequest request) {
        CoachResponse response = coachService.updateCoach(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/coaches/{id}")
    public ResponseEntity<Void> deleteCoach(@PathVariable Long id) {
        coachService.deleteCoach(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/specialties")
    public ResponseEntity<SpecialtyResponse> createSpecialty(@Valid @RequestBody CreateSpecialtyRequest request) {
        SpecialtyResponse response = specialtyService.createSpecialty(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

