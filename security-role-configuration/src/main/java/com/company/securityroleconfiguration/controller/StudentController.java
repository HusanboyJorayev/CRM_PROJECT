package com.company.securityroleconfiguration.controller;

import com.company.securityroleconfiguration.dto.ResponseDto;
import com.company.securityroleconfiguration.dto.SimpleCrud;
import com.company.securityroleconfiguration.dto.StudentDto;
import com.company.securityroleconfiguration.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "student")
public class StudentController implements SimpleCrud<Long, StudentDto> {

    private final StudentService studentService;

    @Override
    @PostMapping(value = "/create")
//    @PreAuthorize(value = "hasAuthority('ROLE_USER')")
    //@PreAuthorize(value = "hasAuthority('ADMIN') or  hasAuthority('CEO')")
    public ResponseDto<StudentDto> create(@RequestBody StudentDto dto) {
        return this.studentService.create(dto);
    }

    @Override
    //@PreAuthorize(value = "hasAnyRole('TEACHER','ADMIN')")
    @GetMapping(value = "/get")
    //@PreAuthorize(value = "hasAuthority('ADMIN')")
    public ResponseDto<StudentDto> get(@RequestParam Long id) {
        return this.studentService.get(id);
    }

    @Override
   // @PreAuthorize(value = "hasRole('ADMIN')")
    @PutMapping(value = "/update")
    //@PreAuthorize(value = "hasAuthority('ADMIN') ")
    public ResponseDto<StudentDto> update(@RequestBody StudentDto dto, @RequestParam Long id) {
        return this.studentService.update(dto, id);
    }

    @Override
    @DeleteMapping(value = "/delete")
    //@PreAuthorize(value = "hasRole('ADMIN')")
    //@PreAuthorize(value = "hasAuthority('ADMIN')")
    public ResponseDto<StudentDto> delete(@RequestParam Long id) {
        return this.studentService.delete(id);
    }


    @GetMapping(value = "/getAll")
    //@PreAuthorize(value = "hasAnyRole('TEACHER','ADMIN')")
    //@PreAuthorize(value = "hasAuthority('ADMIN') or  hasAuthority('CEO')")
    public ResponseDto<List<StudentDto>> getAll() {
        return this.studentService.getAll();
    }
}
