package com.fitnest.webbackend.repositories;

import com.fitnest.webbackend.model.entities.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {
    Set<Specialty> findAllByIdIn(Set<Long> ids);
    boolean existsByName(String name);
}

