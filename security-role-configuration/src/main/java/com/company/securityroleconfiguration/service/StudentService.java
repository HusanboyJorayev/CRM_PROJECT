package com.company.securityroleconfiguration.service;

import com.company.securityroleconfiguration.dto.ResponseDto;
import com.company.securityroleconfiguration.dto.SimpleCrud;
import com.company.securityroleconfiguration.dto.StudentDto;
import com.company.securityroleconfiguration.module.Student;
import com.company.securityroleconfiguration.repository.StudentRepository;
import com.company.securityroleconfiguration.service.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService implements SimpleCrud<Long, StudentDto> {

    private final StudentMapper studentMapper;
    private final StudentRepository studentRepository;

    @Override
    public ResponseDto<StudentDto> create(StudentDto dto) {

        try {
            Student student = this.studentMapper.toEntity(dto);
            student.setCreatedAt(LocalDateTime.now());
            this.studentRepository.save(student);

            return ResponseDto.<StudentDto>builder()
                    .success(true)
                    .message("OK")
                    .data(this.studentMapper.toDto(student))
                    .build();

        } catch (Exception e) {
            return ResponseDto.<StudentDto>builder()
                    .code(-1)
                    .message("Student while creating error")
                    .build();
        }
    }

    @Override
    public ResponseDto<StudentDto> get(Long id) {

        Optional<Student> optional = this.studentRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return ResponseDto.<StudentDto>builder()
                    .code(-1)
                    .message(String.format("Student is not found this id %d::", id))
                    .build();
        }
        Student student = optional.get();
        return ResponseDto.<StudentDto>builder()
                .success(true)
                .message("Ok")
                .data(this.studentMapper.toDto(student))
                .build();
    }

    @Override
    public ResponseDto<StudentDto> update(StudentDto dto, Long id) {

        try {
            Optional<Student> optional = this.studentRepository.findByIdAndDeletedAtIsNull(id);
            if (optional.isEmpty()) {
                return ResponseDto.<StudentDto>builder()
                        .code(-1)
                        .message(String.format("Student is not found this id %d::", id))
                        .build();
            }
            Student student = optional.get();
            student.setUpdatedAt(LocalDateTime.now());
            this.studentMapper.update(student, dto);
            this.studentRepository.save(student);

            return ResponseDto.<StudentDto>builder()
                    .success(true)
                    .message("OK")
                    .data(this.studentMapper.toDto(student))
                    .build();

        } catch (Exception e) {
            return ResponseDto.<StudentDto>builder()
                    .code(-1)
                    .message(String.format("Student while updating error this id %d::", id))
                    .build();
        }

    }

    @Override
    public ResponseDto<StudentDto> delete(Long id) {

        Optional<Student> optional = this.studentRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return ResponseDto.<StudentDto>builder()
                    .code(-1)
                    .message(String.format("Student is not found this id %d::", id))
                    .build();
        }
        Student student = optional.get();
        student.setDeletedAt(LocalDateTime.now());
        this.studentRepository.save(student);

        return ResponseDto.<StudentDto>builder()
                .success(true)
                .message("Ok")
                .data(this.studentMapper.toDto(student))
                .build();
    }


    public ResponseDto<List<StudentDto>> getAll() {

        List<Student> list = this.studentRepository.findAllByDeletedAtIsNull();
        if (list.isEmpty()) {
            return ResponseDto.<List<StudentDto>>builder()
                    .code(-1)
                    .message("Students are not found")
                    .build();
        }
        return ResponseDto.<List<StudentDto>>builder()
                .success(true)
                .message("Ok")
                .data(list.stream().map(this.studentMapper::toDto).toList())
                .build();
    }
}
