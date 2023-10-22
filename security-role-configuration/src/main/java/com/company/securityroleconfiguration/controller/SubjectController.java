package com.company.securityroleconfiguration.controller;

import com.company.securityroleconfiguration.dto.ResponseDto;
import com.company.securityroleconfiguration.dto.SimpleCrud;
import com.company.securityroleconfiguration.dto.SubjectDto;
import com.company.securityroleconfiguration.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "subject")
public class SubjectController implements SimpleCrud<Integer, SubjectDto> {

    private final SubjectService subjectService;

    @Override
    @PostMapping(value = "/create")
   // @PreAuthorize(value = "hasRole('ADMIN')")
    //@PreAuthorize(value = "hasAuthority('CEO')")
    public ResponseDto<SubjectDto> create(@RequestBody SubjectDto dto) {
        return this.subjectService.create(dto);
    }

    @Override
    @GetMapping(value = "/get")
    //@PreAuthorize(value = "hasRole('ADMIN')")
    //@PreAuthorize(value = "hasAuthority('CEO')")
    public ResponseDto<SubjectDto> get(@RequestParam Integer id) {
        return this.subjectService.get(id);
    }

    @Override
    @PutMapping(value = "/update")
    //@PreAuthorize(value = "hasRole('ADMIN')")
    //@PreAuthorize(value = "hasAuthority('CEO')")
    public ResponseDto<SubjectDto> update(@RequestBody SubjectDto dto, @RequestParam Integer id) {
        return this.subjectService.update(dto, id);
    }

    @Override
    @DeleteMapping(value = "/delete")
    //@PreAuthorize(value = "hasRole('ADMIN')")
    //@PreAuthorize(value = "hasAuthority('CEO')")
    public ResponseDto<SubjectDto> delete(Integer id) {
        return this.subjectService.delete(id);
    }

    @GetMapping(value = "/getAll")
    //@PreAuthorize(value = "hasAnyRole('USER','ADMIN')")
    //@PreAuthorize(value = "hasAuthority('CEO')")
    public ResponseDto<List<SubjectDto>> getAll() {
        return this.subjectService.getAll();
    }
}
