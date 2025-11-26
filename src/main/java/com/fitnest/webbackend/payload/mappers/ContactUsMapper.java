package com.fitnest.webbackend.payload.mappers;

import com.fitnest.webbackend.model.entities.ContactUs;
import com.fitnest.webbackend.payload.dtos.ContactUsRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContactUsMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    ContactUs toEntity(ContactUsRequestDto contactRequestDto);

}
