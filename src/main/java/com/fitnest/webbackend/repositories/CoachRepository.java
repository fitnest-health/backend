package com.fitnest.webbackend.repositories;

import com.fitnest.webbackend.model.entities.Coach;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoachRepository extends JpaRepository<Coach, Long> {
    boolean existsByEmail(String email);
}

