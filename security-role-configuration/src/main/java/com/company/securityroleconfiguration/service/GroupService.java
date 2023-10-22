package com.company.securityroleconfiguration.service;


import com.company.securityroleconfiguration.dto.GroupsDto;
import com.company.securityroleconfiguration.dto.ResponseDto;
import com.company.securityroleconfiguration.dto.SimpleCrud;
import com.company.securityroleconfiguration.module.Groups;
import com.company.securityroleconfiguration.repository.GroupsRepository;
import com.company.securityroleconfiguration.service.mapper.GroupMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupService implements SimpleCrud<Integer, GroupsDto> {
    private final GroupsRepository groupsRepository;
    private final GroupMapper groupMapper;

    @Override
    public ResponseDto<GroupsDto> create(GroupsDto dto) {
        try {
            Groups groups = this.groupMapper.toEntity(dto);
            groups.setCreatedAt(LocalDateTime.now());
            this.groupsRepository.save(groups);

            return ResponseDto.<GroupsDto>builder()
                    .success(true)
                    .message("Ok")
                    .data(this.groupMapper.toDto(groups))
                    .build();

        } catch (Exception e) {

            return ResponseDto.<GroupsDto>builder()
                    .code(-1)
                    .message("groups while saving error")
                    .build();
        }
    }

    @Override
    public ResponseDto<GroupsDto> get(Integer id) {
        Optional<Groups> optional = this.groupsRepository.findByGroupIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {

            return ResponseDto.<GroupsDto>builder()
                    .code(-1)
                    .message("Group is not found")
                    .build();
        }
        Groups groups = optional.get();
        return ResponseDto.<GroupsDto>builder()
                .success(true)
                .message("Ok")
                .data(this.groupMapper.toDto(groups))
                .build();
    }

    @Override
    public ResponseDto<GroupsDto> update(GroupsDto dto, Integer id) {
        try {
            Optional<Groups> optional = this.groupsRepository.findByGroupIdAndDeletedAtIsNull(id);
            if (optional.isEmpty()) {
                return ResponseDto.<GroupsDto>builder()
                        .code(-1)
                        .message("Group is not fund")
                        .build();
            }
            Groups groups = optional.get();
            groups.setUpdatedAt(LocalDateTime.now());
            this.groupMapper.update(groups, dto);
            this.groupsRepository.save(groups);

            return ResponseDto.<GroupsDto>builder()
                    .success(true)
                    .message("Ok")
                    .data(this.groupMapper.toDto(groups))
                    .build();
        } catch (Exception e) {

            return ResponseDto.<GroupsDto>builder()
                    .code(-1)
                    .message("Groups while saving error")
                    .build();
        }
    }

    @Override
    public ResponseDto<GroupsDto> delete(Integer id) {
        var optional = this.groupsRepository.findByGroupIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {

            return ResponseDto.<GroupsDto>builder()
                    .code(-1)
                    .message("Group is not found")
                    .build();
        }
        Groups groups = optional.get();
        groups.setDeletedAt(LocalDateTime.now());
        this.groupsRepository.save(groups);

        return ResponseDto.<GroupsDto>builder()
                .success(true)
                .message("Ok")
                .data(this.groupMapper.toDto(groups))
                .build();
    }

    public ResponseDto<List<GroupsDto>> getAll() {
        var optional = this.groupsRepository.findAllByDeletedAtIsNull();
        if (optional.isEmpty()) {
            return ResponseDto.<List<GroupsDto>>builder()
                    .code(-1)
                    .message("groups are not found")
                    .build();
        }
        return ResponseDto.<List<GroupsDto>>builder()
                .success(true)
                .message("Ok")
                .data(optional.stream().map(this.groupMapper::toDto).toList())
                .build();
    }
}
