package com.fitnest.webbackend.repositories;

import com.fitnest.webbackend.model.entities.ContactUs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactMessageRepository extends JpaRepository<ContactUs, Long> {
}
