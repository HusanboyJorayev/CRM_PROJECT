package com.company.securityroleconfiguration.service.mapper;

import com.company.securityroleconfiguration.dto.SubjectDto;
import com.company.securityroleconfiguration.module.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class SubjectMapper {

    public abstract SubjectDto toDto(Subject subject);

    @Mapping(ignore = true,target = "subId")
    @Mapping(ignore = true,target = "createdAt")
    @Mapping(ignore = true,target = "updatedAt")
    @Mapping(ignore = true,target = "deletedAt")
    public abstract Subject toEntity(SubjectDto subjectDto);

    public abstract void update(@MappingTarget Subject subject,SubjectDto subjectDto);
}
