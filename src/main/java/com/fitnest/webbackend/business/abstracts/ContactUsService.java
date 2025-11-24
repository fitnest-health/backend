package com.fitnest.webbackend.business.abstracts;

import com.fitnest.webbackend.payload.dtos.ContactUsRequestDto;

public interface ContactUsService {

    void sendMessage(ContactUsRequestDto contactRequestDto);
}
