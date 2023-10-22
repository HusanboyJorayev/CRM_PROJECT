package com.company.securityroleconfiguration.service;


import com.company.securityroleconfiguration.dto.ResponseDto;
import com.company.securityroleconfiguration.dto.RoomDto;
import com.company.securityroleconfiguration.dto.SimpleCrud;
import com.company.securityroleconfiguration.module.Room;
import com.company.securityroleconfiguration.repository.RoomRepository;
import com.company.securityroleconfiguration.service.mapper.RoomMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class RoomService implements SimpleCrud<Integer, RoomDto> {

    private final RoomMapper roomMapper;
    private final RoomRepository roomRepository;

    @Override
    public ResponseDto<RoomDto> create(RoomDto dto) {

        try {
            Room room = this.roomMapper.toEntity(dto);
            room.setCreatedAt(LocalDateTime.now());
            this.roomRepository.save(room);

            return ResponseDto.<RoomDto>builder()
                    .success(true)
                    .message("Ok")
                    .data(this.roomMapper.toDto(room))
                    .build();

        } catch (Exception e) {

            return ResponseDto.<RoomDto>builder()
                    .code(-1)
                    .message("room while creating error")
                    .build();
        }
    }

    @Override
    public ResponseDto<RoomDto> get(Integer id) {

        Optional<Room> optional = this.roomRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {

            return ResponseDto.<RoomDto>builder()
                    .code(-1)
                    .message("room is not found")
                    .build();
        }
        var room = optional.get();
        return ResponseDto.<RoomDto>builder()
                .success(true)
                .message("OK")
                .data(this.roomMapper.toDto(room))
                .build();
    }

    @Override
    public ResponseDto<RoomDto> update(RoomDto dto, Integer id) {

        try {
            Optional<Room> optional = this.roomRepository.findByIdAndDeletedAtIsNull(id);
            if (optional.isEmpty()) {
                return ResponseDto.<RoomDto>builder()
                        .code(-1)
                        .message(String.format("room is not found this id %d::", id))
                        .build();
            }
            var room = optional.get();
            room.setUpdatedAt(LocalDateTime.now());
            this.roomRepository.save(room);

            return ResponseDto.<RoomDto>builder()
                    .success(true)
                    .message("Ok")
                    .data(this.roomMapper.toDto(room))
                    .build();
        } catch (Exception e) {

            return ResponseDto.<RoomDto>builder()
                    .code(-1)
                    .message("room  while updating error")
                    .build();
        }
    }

    @Override
    public ResponseDto<RoomDto> delete(Integer id) {
        Optional<Room> optional = this.roomRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {

            return ResponseDto.<RoomDto>builder()
                    .code(-1)
                    .message(String.format("Room is not found this id %d ::", id))
                    .build();
        }
        var room = optional.get();
        room.setUpdatedAt(LocalDateTime.now());
        this.roomRepository.save(room);

        return ResponseDto.<RoomDto>builder()
                .success(true)
                .message("Ok")
                .data(this.roomMapper.toDto(room))
                .build();
    }

    public ResponseDto<List<RoomDto>> getAll() {
        List<Room> room = this.roomRepository.findAllByDeletedAtIsNull();
        if (room.isEmpty()) {
            return ResponseDto.<List<RoomDto>>builder()
                    .code(-1)
                    .message("Rooms are not found")
                    .build();
        }
        return ResponseDto.<List<RoomDto>>builder()
                .success(true)
                .message("Ok")
                .data(room.stream().map(this.roomMapper::toDto).toList())
                .build();
    }
}
