package com.fitnest.webbackend.payload.mappers;

import com.fitnest.webbackend.model.entities.Specialty;
import com.fitnest.webbackend.payload.dtos.specialty.CreateSpecialtyRequest;
import com.fitnest.webbackend.payload.dtos.specialty.SpecialtyResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SpecialtyMapper {

    @Mapping(target = "id", ignore = true)
    Specialty toEntity(CreateSpecialtyRequest request);

    SpecialtyResponse toResponse(Specialty specialty);
}

