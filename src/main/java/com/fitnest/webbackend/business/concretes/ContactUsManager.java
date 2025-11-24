package com.fitnest.webbackend.business.concretes;

import com.fitnest.webbackend.business.abstracts.ContactUsService;
import com.fitnest.webbackend.model.entities.ContactUs;
import com.fitnest.webbackend.payload.dtos.ContactUsRequestDto;
import com.fitnest.webbackend.payload.mappers.ContactUsMapper;
import com.fitnest.webbackend.repositories.ContactMessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContactUsManager implements ContactUsService {

    private final ContactMessageRepository contactMessageRepository;
    private final ContactUsMapper contactUsMapper;

    @Override
    public void sendMessage(ContactUsRequestDto contactRequestDto) {
        log.info("ActionLog.sendMessage().start : {}", contactRequestDto.toString());
        ContactUs message = contactUsMapper.toEntity(contactRequestDto);
        contactMessageRepository.save(message);
        log.info("ActionLog.sendMessage().end : messageId={}", message.getId());
    }
}
