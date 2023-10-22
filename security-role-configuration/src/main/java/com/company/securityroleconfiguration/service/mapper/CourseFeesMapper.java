package com.company.securityroleconfiguration.service.mapper;

import com.company.securityroleconfiguration.dto.CourseFeesDto;
import com.company.securityroleconfiguration.module.CourseFees;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class CourseFeesMapper {

    public abstract CourseFeesDto toDto(CourseFees courseFees);

    @Mapping(ignore = true,target = "id")
    @Mapping(ignore = true,target = "createdAt")
    @Mapping(ignore = true,target = "updatedAt")
    @Mapping(ignore = true,target = "deletedAt")
    public abstract CourseFees toEntity(CourseFeesDto courseFeesDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget CourseFees courseFees,CourseFeesDto courseFeesDto);
}
