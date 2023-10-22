package com.company.securityroleconfiguration.controller;

import com.company.securityroleconfiguration.dto.AuthoritiesDto;
import com.company.securityroleconfiguration.dto.ResponseDto;
import com.company.securityroleconfiguration.dto.SimpleCrud;
import com.company.securityroleconfiguration.dto.request.RequestAuthDto;
import com.company.securityroleconfiguration.module.Authorities;
import com.company.securityroleconfiguration.service.AuthorityService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Auth")
public class AuthorityController implements SimpleCrud<Integer, AuthoritiesDto> {

    private final AuthorityService authorityService;

    @Override
    @PostMapping(value = "/create")
    //@PreAuthorize(value = "hasAuthority('ADMIN') or  hasAuthority('CEO')")
    public ResponseDto<AuthoritiesDto> create(@RequestBody AuthoritiesDto dto) {
        return this.authorityService.create(dto);
    }

    @Override
    @GetMapping(value = "/get")
    //@PreAuthorize(value = "hasAuthority('ADMIN') or  hasAuthority('CEO')")
    public ResponseDto<AuthoritiesDto> get(@RequestParam Integer id) {
        return this.authorityService.get(id);
    }

    @Override
    @PutMapping(value = "/update")
    //@PreAuthorize(value = "hasAuthority('ADMIN') or  hasAuthority('CEO')")
    public ResponseDto<AuthoritiesDto> update(@RequestBody AuthoritiesDto dto, @RequestParam Integer id) {
        return this.authorityService.update(dto, id);
    }

    @Override
    @DeleteMapping(value = "/delete")
    //@PreAuthorize(value = "hasAuthority('ADMIN') or  hasAuthority('CEO')")
    public ResponseDto<AuthoritiesDto> delete(@RequestParam Integer id) {
        return this.authorityService.delete(id);
    }


}
