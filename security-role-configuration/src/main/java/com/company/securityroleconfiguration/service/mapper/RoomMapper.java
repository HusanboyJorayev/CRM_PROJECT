package com.company.securityroleconfiguration.service.mapper;


import com.company.securityroleconfiguration.dto.RoomDto;
import com.company.securityroleconfiguration.module.Room;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class RoomMapper {

    public abstract RoomDto toDto(Room room);

    @Mapping(ignore = true,target = "id")
    @Mapping(ignore = true,target = "createdAt")
    @Mapping(ignore = true,target = "updatedAt")
    @Mapping(ignore = true,target = "deletedAt")
    public abstract Room toEntity(RoomDto roomDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget Room room,RoomDto roomDto);
}
