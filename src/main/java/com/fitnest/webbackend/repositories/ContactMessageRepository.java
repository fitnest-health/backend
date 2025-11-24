package com.fitnest.webbackend.repositories;

import com.fitnest.webbackend.model.entities.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {
}
