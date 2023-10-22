package com.company.securityroleconfiguration.service.mapper;


import com.company.securityroleconfiguration.dto.StudentDto;
import com.company.securityroleconfiguration.module.Student;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class StudentMapper {

    public abstract StudentDto toDto(Student student);

    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "createdAt")
    @Mapping(ignore = true, target = "updatedAt")
    @Mapping(ignore = true, target = "deletedAt")
    public abstract Student toEntity(StudentDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget Student student, StudentDto dto);
}
