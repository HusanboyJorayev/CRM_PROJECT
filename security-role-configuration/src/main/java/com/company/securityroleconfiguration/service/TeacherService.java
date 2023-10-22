package com.company.securityroleconfiguration.service;


import com.company.securityroleconfiguration.dto.ResponseDto;
import com.company.securityroleconfiguration.dto.SimpleCrud;
import com.company.securityroleconfiguration.dto.TeacherDto;
import com.company.securityroleconfiguration.module.Teacher;
import com.company.securityroleconfiguration.repository.TeacherRepository;
import com.company.securityroleconfiguration.service.mapper.TeacherMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherService implements SimpleCrud<Integer, TeacherDto> {
    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    @Override
    public ResponseDto<TeacherDto> create(TeacherDto dto) {
        try {
            Teacher teacher = this.teacherMapper.toEntity(dto);
            teacher.setCreatedAt(LocalDateTime.now());
            this.teacherRepository.save(teacher);

            return ResponseDto.<TeacherDto>builder()
                    .success(true)
                    .message("Ok")
                    .data(this.teacherMapper.toDto(teacher))
                    .build();

        } catch (Exception e) {

            return ResponseDto.<TeacherDto>builder()
                    .code(-1)
                    .message("teacher while saving error")
                    .build();
        }
    }

    @Override
    public ResponseDto<TeacherDto> get(Integer id) {

        Optional<Teacher> optional = this.teacherRepository.findByTeacherIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return ResponseDto.<TeacherDto>builder()
                    .code(-1)
                    .message(String.format("Teacher is not found this %d::id", id))
                    .build();
        }
        Teacher teacher = optional.get();

        return ResponseDto.<TeacherDto>builder()
                .success(true)
                .message("Ok")
                .data(this.teacherMapper.toDto(teacher))
                .build();
    }

    @Override
    public ResponseDto<TeacherDto> update(TeacherDto dto, Integer id) {
        Optional<Teacher> optional = this.teacherRepository.findByTeacherIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return ResponseDto.<TeacherDto>builder()
                    .code(-1)
                    .message(String.format("Teacher is not found this %d::id", id))
                    .build();
        }
        try {

            Teacher teacher = optional.get();
            teacher.setUpdatedAt(LocalDateTime.now());
            this.teacherMapper.update(teacher, dto);
            this.teacherRepository.save(teacher);

            return ResponseDto.<TeacherDto>builder()
                    .success(true)
                    .message("Ok")
                    .data(this.teacherMapper.toDto(teacher))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<TeacherDto>builder()
                    .code(-1)
                    .message("teacher while updating error")
                    .build();
        }

    }

    @Override
    public ResponseDto<TeacherDto> delete(Integer id) {
        Optional<Teacher> optional = this.teacherRepository.findByTeacherIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return ResponseDto.<TeacherDto>builder()
                    .code(-1)
                    .message(String.format("Teacher is not found this %d::id", id))
                    .build();
        }
        Teacher teacher = optional.get();
        teacher.setDeletedAt(LocalDateTime.now());
        return ResponseDto.<TeacherDto>builder()
                .success(true)
                .message("Ok")
                .data(this.teacherMapper.toDto(teacher))
                .build();
    }


    public ResponseDto<List<TeacherDto>> getAll() {
        List<Teacher> teacher = this.teacherRepository.findAllByDeletedAtIsNull();
        if (teacher.isEmpty()) {
            return ResponseDto.<List<TeacherDto>>builder()
                    .code(-1)
                    .message("Teachers are not found")
                    .build();
        }
        return ResponseDto.<List<TeacherDto>>builder()
                .success(true)
                .message("Ok")
                .data(teacher.stream().map(this.teacherMapper::toDto).toList())
                .build();
    }
}
