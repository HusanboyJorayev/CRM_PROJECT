package com.company.securityroleconfiguration.service.mapper;


import com.company.securityroleconfiguration.dto.SalaryDto;
import com.company.securityroleconfiguration.module.Salary;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class SalaryMapper {

    public abstract SalaryDto toDto(Salary salary);

    @Mapping(ignore = true,target = "id")
    @Mapping(ignore = true,target = "createdAt")
    @Mapping(ignore = true,target = "updatedAt")
    @Mapping(ignore = true,target = "deletedAt")
    public abstract Salary toEntity(SalaryDto salaryDto);


    public abstract void update(@MappingTarget Salary salary,SalaryDto salaryDto);
}
