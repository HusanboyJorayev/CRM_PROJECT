package com.company.securityroleconfiguration.controller;


import com.company.securityroleconfiguration.dto.ResponseDto;
import com.company.securityroleconfiguration.dto.SalaryDto;
import com.company.securityroleconfiguration.dto.SimpleCrud;
import com.company.securityroleconfiguration.service.SalaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "salary")
public class SalaryController implements SimpleCrud<Integer, SalaryDto> {

    private final SalaryService salaryService;

    @Override
    @PostMapping(value = "/create")
   // @PreAuthorize(value = "hasRole('ADMIN')")
    //@PreAuthorize(value = "hasAuthority('CEO')")
    public ResponseDto<SalaryDto> create(@RequestBody SalaryDto dto) {
        return this.salaryService.create(dto);
    }

    @Override
    @GetMapping(value = "/get")
    //@PreAuthorize(value = "hasRole('ADMIN')")
    //@PreAuthorize(value = "hasAuthority('CEO')")
    public ResponseDto<SalaryDto> get(@RequestParam Integer id) {
        return this.salaryService.get(id);
    }

    @Override
    @PostMapping(value = "/update")
    //@PreAuthorize(value = "hasRole('ADMIN')")
    //@PreAuthorize(value = "hasAuthority('CEO')")
    public ResponseDto<SalaryDto> update(@RequestBody SalaryDto dto,@RequestParam Integer id) {
        return this.salaryService.update(dto, id);
    }

    @Override
    @DeleteMapping(value = "/delete")
    //@PreAuthorize(value = "hasRole('ADMIN')")
    //@PreAuthorize(value = "hasAuthority('CEO')")
    public ResponseDto<SalaryDto> delete(@RequestParam Integer id) {
        return this.salaryService.delete(id);
    }

    @GetMapping(value = "/getAll")
    //@PreAuthorize(value = "hasRole('ADMIN')")
    //@PreAuthorize(value = "hasAuthority('CEO')")
    public ResponseDto<List<SalaryDto>>getAll(){
        return this.salaryService.getAll();
    }
}
