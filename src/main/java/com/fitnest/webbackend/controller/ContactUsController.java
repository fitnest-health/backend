package com.fitnest.webbackend.controller;

import com.fitnest.webbackend.business.abstracts.ContactUsService;
import com.fitnest.webbackend.payload.dtos.ContactUsRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contact-us")
@RequiredArgsConstructor
public class ContactUsController {
    private final ContactUsService contactUsService;

    @PostMapping
    public ResponseEntity<Void> sendMessage(@RequestBody @Valid ContactUsRequestDto contactRequestDto) {
        contactUsService.sendMessage(contactRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



}
