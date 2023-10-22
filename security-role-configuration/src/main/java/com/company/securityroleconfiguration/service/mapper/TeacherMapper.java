package com.company.securityroleconfiguration.service.mapper;


import com.company.securityroleconfiguration.dto.TeacherDto;
import com.company.securityroleconfiguration.module.Teacher;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class TeacherMapper {

    public abstract TeacherDto toDto(Teacher teacher);

    @Mapping(ignore = true, target = "teacherId")
    @Mapping(ignore = true, target = "createdAt")
    @Mapping(ignore = true, target = "updatedAt")
    @Mapping(ignore = true, target = "deletedAt")
    public abstract Teacher toEntity(TeacherDto teacherDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget Teacher teacher, TeacherDto teacherDto);
}
