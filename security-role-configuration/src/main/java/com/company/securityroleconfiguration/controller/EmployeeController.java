package com.company.securityroleconfiguration.controller;


import com.company.securityroleconfiguration.dto.EmployeeDto;
import com.company.securityroleconfiguration.dto.ResponseDto;
import com.company.securityroleconfiguration.dto.SimpleCrud;
import com.company.securityroleconfiguration.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "emp")
public class EmployeeController implements SimpleCrud<Integer, EmployeeDto> {

    private final EmployeeService employeeService;

    @Override
    @PostMapping(value = "/create")
   // @PreAuthorize(value = "hasRole('ADMIN')")
    //@PreAuthorize(value = "hasAuthority('CEO')")
    public ResponseDto<EmployeeDto> create(@RequestBody EmployeeDto dto) {
        return this.employeeService.create(dto);
    }

    @Override
    @GetMapping(value = "/get")
    //@PreAuthorize(value = "hasRole('ADMIN')")
    //@PreAuthorize(value = "hasAuthority('CEO')")
    public ResponseDto<EmployeeDto> get(@RequestParam Integer id) {
        return this.employeeService.get(id);
    }

    @Override
    @PutMapping(value = "/update")
    //@PreAuthorize(value = "hasRole('ADMIN')")
    //@PreAuthorize(value = "hasAuthority('CEO')")
    public ResponseDto<EmployeeDto> update(@RequestBody EmployeeDto dto, @RequestParam Integer id) {
        return this.employeeService.update(dto, id);
    }

    @Override
    @DeleteMapping(value = "/delete")
    //@PreAuthorize(value = "hasRole('ADMIN')")
    //@PreAuthorize(value = "hasAuthority('CEO')")
    public ResponseDto<EmployeeDto> delete(@RequestParam Integer id) {
        return this.employeeService.delete(id);
    }

    @GetMapping(value = "/getAll")
    //@PreAuthorize(value = "hasRole('ADMIN')")
    //@PreAuthorize(value = "hasAuthority('CEO')")
    public ResponseDto<List<EmployeeDto>> getAll() {
        return this.employeeService.getAll();
    }
}
