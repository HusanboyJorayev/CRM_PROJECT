package com.company.securityroleconfiguration.controller;


import com.company.securityroleconfiguration.dto.GroupsDto;
import com.company.securityroleconfiguration.dto.ResponseDto;
import com.company.securityroleconfiguration.dto.SimpleCrud;
import com.company.securityroleconfiguration.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "groups")
public class GroupsController implements SimpleCrud<Integer, GroupsDto> {

    private final GroupService groupService;


    @Override
    @PostMapping(value = "/create")
    //@PreAuthorize(value = "hasAnyRole('ADMIN','CEO')")
    //@PreAuthorize(value = "hasAuthority('ADMIN') or  hasAuthority('CEO')")
    public ResponseDto<GroupsDto> create(@RequestBody GroupsDto dto) {
        return this.groupService.create(dto);
    }

    @Override
    @GetMapping(value = "/get")
    //@PreAuthorize(value = "hasAnyRole('ADMIN','CEO')")
    //@PreAuthorize(value = "hasAuthority('ADMIN') or  hasAuthority('CEO')")
    public ResponseDto<GroupsDto> get(@RequestParam Integer id) {
        return this.groupService.get(id);
    }

    @Override
    @PutMapping(value = "/put")
    //@PreAuthorize(value = "hasAnyRole('ADMIN','CEO')")
    //@PreAuthorize(value = "hasAuthority('ADMIN') or  hasAuthority('CEO')")
    public ResponseDto<GroupsDto> update(@RequestBody GroupsDto dto, @RequestParam Integer id) {
        return this.groupService.update(dto, id);
    }

    @Override
    @DeleteMapping(value = "/delete")
    //@PreAuthorize(value = "hasAnyRole('ADMIN','CEO')")
    //@PreAuthorize(value = "hasAuthority('ADMIN') or  hasAuthority('CEO')")
    public ResponseDto<GroupsDto> delete(@RequestParam Integer id) {
        return this.groupService.delete(id);
    }

    @GetMapping(value = "/getAll")
    //@PreAuthorize(value = "hasAnyRole('ADMIN','CEO')")
    //@PreAuthorize(value = "hasAuthority('ADMIN') or  hasAuthority('CEO')")
    public ResponseDto<List<GroupsDto>> getAll() {
        return this.groupService.getAll();
    }
}
