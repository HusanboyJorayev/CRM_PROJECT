package com.company.securityroleconfiguration.controller;

import com.company.securityroleconfiguration.dto.FinanceDto;
import com.company.securityroleconfiguration.dto.ResponseDto;
import com.company.securityroleconfiguration.dto.SimpleCrud;
import com.company.securityroleconfiguration.service.FinanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "finance")
public class FinanceController implements SimpleCrud<Integer, FinanceDto> {

    private final FinanceService financeService;

    @Override
    @PostMapping(value = "/create")
    //@PreAuthorize(value = "hasRole('CEO')")
    //@PreAuthorize(value = "hasAuthority('CEO')")
    public ResponseDto<FinanceDto> create(@RequestBody FinanceDto dto) {
        return this.financeService.create(dto);
    }

    @Override
    @GetMapping(value = "/get")
    //@PreAuthorize(value = "hasRole('CEO')")
    //@PreAuthorize(value = "hasAuthority('CEO')")
    public ResponseDto<FinanceDto> get(@RequestParam Integer id) {
        return this.financeService.get(id);
    }

    @Override
    @PutMapping(value = "/update")
    //@PreAuthorize(value = "hasRole('CEO')")
    //@PreAuthorize(value = "hasAuthority('CEO')")
    public ResponseDto<FinanceDto> update(@RequestBody FinanceDto dto,@RequestParam Integer id) {
        return this.financeService.update(dto, id);
    }

    @Override
    @DeleteMapping(value = "/delete")
    //@PreAuthorize(value = "hasRole('CEO')")
    //@PreAuthorize(value = "hasAuthority('CEO')")
    public ResponseDto<FinanceDto> delete(@RequestParam Integer id) {
        return this.financeService.delete(id);
    }

    @GetMapping(value = "/getAll")
    //@PreAuthorize(value = "hasRole('CEO')")
    //@PreAuthorize(value = "hasAuthority('CEO')")
    public ResponseDto<List<FinanceDto>>getAll(){
        return this.financeService.getAll();
    }
}
