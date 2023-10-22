package com.company.securityroleconfiguration.service.mapper;


import com.company.securityroleconfiguration.dto.GroupsDto;
import com.company.securityroleconfiguration.module.Groups;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class GroupMapper {

    public abstract GroupsDto toDto(Groups groups);

    @Mapping(ignore = true,target = "groupId")
    @Mapping(ignore = true,target = "createdAt")
    @Mapping(ignore = true,target = "updatedAt")
    @Mapping(ignore = true,target = "deletedAt")
    public abstract Groups toEntity(GroupsDto groupsDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget Groups groups,GroupsDto groupsDto);
}
