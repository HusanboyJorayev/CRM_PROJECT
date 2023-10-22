package com.company.securityroleconfiguration.controller;


import com.company.securityroleconfiguration.dto.ResponseDto;
import com.company.securityroleconfiguration.dto.RoomDto;
import com.company.securityroleconfiguration.dto.SimpleCrud;
import com.company.securityroleconfiguration.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "room")
public class RoomController implements SimpleCrud<Integer, RoomDto> {

    private final RoomService roomService;

    @Override
    @PostMapping(value = "/create")
    //@PreAuthorize(value = "hasAnyRole('ADMIN','CEO')")
    //@PreAuthorize(value = "hasAuthority('ADMIN') or  hasAuthority('CEO')")
    public ResponseDto<RoomDto> create(@RequestBody RoomDto dto) {
        return this.roomService.create(dto);
    }

    @Override
    @GetMapping(value = "/get")
    //@PreAuthorize(value = "hasAnyRole('ADMIN','CEO')")
    //@PreAuthorize(value = "hasAuthority('ADMIN') or  hasAuthority('CEO')")
    public ResponseDto<RoomDto> get(@RequestParam Integer id) {
        return this.roomService.get(id);
    }

    @Override
    @PutMapping(value = "/update")
    //@PreAuthorize(value = "hasAnyRole('ADMIN','CEO')")
    //@PreAuthorize(value = "hasAuthority('ADMIN') or  hasAuthority('CEO')")
    public ResponseDto<RoomDto> update(@RequestBody RoomDto dto, @RequestParam Integer id) {
        return this.roomService.update(dto, id);
    }

    @Override
    @DeleteMapping(value = "/delete")
    //@PreAuthorize(value = "hasAnyRole('ADMIN','CEO')")
    //@PreAuthorize(value = "hasAuthority('ADMIN') or  hasAuthority('CEO')")
    public ResponseDto<RoomDto> delete(@RequestParam Integer id) {
        return this.roomService.delete(id);
    }

    @GetMapping(value = "/getAll")
    //@PreAuthorize(value = "hasAnyRole('ADMIN','CEO')")
    //@PreAuthorize(value = "hasAuthority('ADMIN') or  hasAuthority('CEO')")
    public ResponseDto<List<RoomDto>> getAll() {
        return this.roomService.getAll();
    }
}
