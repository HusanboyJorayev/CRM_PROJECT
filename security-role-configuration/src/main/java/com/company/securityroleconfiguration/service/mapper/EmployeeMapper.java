package com.company.securityroleconfiguration.service.mapper;


import com.company.securityroleconfiguration.dto.EmployeeDto;
import com.company.securityroleconfiguration.module.Employee;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class EmployeeMapper {

    public abstract EmployeeDto toDto(Employee employee);

    @Mapping(ignore = true,target = "empId")
    @Mapping(ignore = true,target = "createdAt")
    @Mapping(ignore = true,target = "updatedAt")
    @Mapping(ignore = true,target = "deletedAt")
    public abstract Employee toEntity(EmployeeDto employeeDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update (@MappingTarget Employee employee,EmployeeDto employeeDto);

}
