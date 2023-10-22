package com.company.securityroleconfiguration.service.mapper;


import com.company.securityroleconfiguration.dto.FinanceDto;
import com.company.securityroleconfiguration.module.Finance;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class FinanceMapper {

    public abstract FinanceDto toDto(Finance finance);

    @Mapping(ignore = true,target = "id")
    @Mapping(ignore = true,target = "createdAt")
    @Mapping(ignore = true,target = "updatedAt")
    @Mapping(ignore = true,target = "deletedAt")
    public abstract Finance toEntity(FinanceDto financeDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget Finance finance,FinanceDto financeDto);
}
