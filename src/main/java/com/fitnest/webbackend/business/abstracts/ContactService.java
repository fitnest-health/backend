package com.fitnest.webbackend.business.abstracts;

import com.fitnest.webbackend.payload.dtos.ContactRequestDto;

public interface ContactService {

    void sendMessage(ContactRequestDto contactRequestDto);
}
