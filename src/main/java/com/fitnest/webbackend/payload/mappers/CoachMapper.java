package com.fitnest.webbackend.payload.mappers;

import com.fitnest.webbackend.model.entities.Coach;
import com.fitnest.webbackend.model.entities.Specialty;
import com.fitnest.webbackend.payload.dtos.coach.CreateCoachRequest;
import com.fitnest.webbackend.payload.dtos.coach.CoachResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CoachMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "specialties", ignore = true)
    Coach toEntity(CreateCoachRequest request);

    @Mapping(target = "specialtyIds", source = "specialties", qualifiedByName = "mapSpecialtiesToIds")
    CoachResponse toResponse(Coach coach);

    @Named("mapSpecialtiesToIds")
    default Set<Long> mapSpecialtiesToIds(Set<Specialty> specialties) {
        if (specialties == null) {
            return null;
        }
        return specialties.stream()
                .map(Specialty::getId)
                .collect(Collectors.toSet());
    }
}

