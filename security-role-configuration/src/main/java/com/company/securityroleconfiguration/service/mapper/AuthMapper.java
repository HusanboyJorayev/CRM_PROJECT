package com.company.securityroleconfiguration.service.mapper;

import com.company.securityroleconfiguration.dto.AuthoritiesDto;
import com.company.securityroleconfiguration.module.Authorities;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class AuthMapper {

    public abstract AuthoritiesDto toDto(Authorities authorities);

    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "createdAt")
    @Mapping(ignore = true, target = "updatedAt")
    @Mapping(ignore = true, target = "deletedAt")
    public abstract Authorities toEntity(AuthoritiesDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget Authorities authorities, AuthoritiesDto dto);
}
