package com.company.securityroleconfiguration.controller;


import com.company.securityroleconfiguration.dto.ResponseDto;
import com.company.securityroleconfiguration.dto.SimpleCrud;
import com.company.securityroleconfiguration.dto.TeacherDto;
import com.company.securityroleconfiguration.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "teacher")
public class TeacherController implements SimpleCrud<Integer, TeacherDto> {
    private final TeacherService teacherService;

    @Override
    @PostMapping(value = "/create")
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    public ResponseDto<TeacherDto> create(@RequestBody TeacherDto dto) {
        return this.teacherService.create(dto);
    }

    @Override
    @GetMapping(value = "/get")
    @PreAuthorize(value = "hasAuthority('USER')")
    public ResponseDto<TeacherDto> get(@RequestParam Integer id) {
        return this.teacherService.get(id);
    }

    @Override
    @PutMapping(value = "/update")
    public ResponseDto<TeacherDto> update(@RequestBody TeacherDto dto, @RequestParam Integer id) {
        return this.teacherService.update(dto, id);
    }

    @Override
    @DeleteMapping(value = "/delete")
    public ResponseDto<TeacherDto> delete(@RequestParam Integer id) {
        return this.teacherService.delete(id);
    }

    @GetMapping(value = "/getAll")
    ResponseDto<List<TeacherDto>> getAll() {
        return this.teacherService.getAll();
    }
}
