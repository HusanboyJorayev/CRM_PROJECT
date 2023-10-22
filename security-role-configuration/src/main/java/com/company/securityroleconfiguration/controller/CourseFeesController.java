package com.company.securityroleconfiguration.controller;

import com.company.securityroleconfiguration.dto.CourseFeesDto;
import com.company.securityroleconfiguration.dto.ResponseDto;
import com.company.securityroleconfiguration.dto.SimpleCrud;
import com.company.securityroleconfiguration.service.CourseFeesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "coFeeCon")
public class CourseFeesController implements SimpleCrud<Integer, CourseFeesDto> {

    private final CourseFeesService courseFeesService;

    @Override
    @PostMapping(value = "/create")
    //@PreAuthorize(value = "hasRole('ADMIN')")
    //@PreAuthorize(value = "hasAuthority('CEO')")
    public ResponseDto<CourseFeesDto> create(@RequestBody CourseFeesDto dto) {
        return this.courseFeesService.create(dto);
    }

    @Override
    @GetMapping(value = "/get")
   // @PreAuthorize(value = "hasRole('ADMIN')")
    //@PreAuthorize(value = "hasAuthority('CEO')")
    public ResponseDto<CourseFeesDto> get(@RequestParam Integer id) {
        return this.courseFeesService.get(id);
    }

    @Override
    @PutMapping(value = "/update")
    //@PreAuthorize(value = "hasRole('ADMIN')")
    //@PreAuthorize(value = "hasAuthority('CEO')")
    public ResponseDto<CourseFeesDto> update(@RequestBody CourseFeesDto dto, @RequestParam Integer id) {
        return this.courseFeesService.update(dto, id);
    }

    @Override
    @DeleteMapping(value = "/delete")
    //@PreAuthorize(value = "hasRole('ADMIN')")
    //@PreAuthorize(value = "hasAuthority('CEO')")
    public ResponseDto<CourseFeesDto> delete(@RequestParam Integer id) {
        return this.courseFeesService.delete(id);
    }

    @GetMapping(value = "/getAll")
    //@PreAuthorize(value = "hasRole('ADMIN')")
    //@PreAuthorize(value = "hasAuthority('CEO')")
    public ResponseDto<List<CourseFeesDto>> getAll() {
        return this.courseFeesService.getAll();
    }

}
