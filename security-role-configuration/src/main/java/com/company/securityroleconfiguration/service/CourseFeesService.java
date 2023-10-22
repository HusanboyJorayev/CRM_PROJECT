package com.company.securityroleconfiguration.service;


import com.company.securityroleconfiguration.dto.CourseFeesDto;
import com.company.securityroleconfiguration.dto.ResponseDto;
import com.company.securityroleconfiguration.dto.SimpleCrud;
import com.company.securityroleconfiguration.module.CourseFees;
import com.company.securityroleconfiguration.repository.CourseFeesRepository;
import com.company.securityroleconfiguration.service.mapper.CourseFeesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseFeesService implements SimpleCrud<Integer, CourseFeesDto> {

    private final CourseFeesRepository courseFeesRepository;
    private final CourseFeesMapper courseFeesMapper;

    @Override
    public ResponseDto<CourseFeesDto> create(CourseFeesDto dto) {
        try {

            var courseFees = this.courseFeesMapper.toEntity(dto);
            courseFees.setCreatedAt(LocalDateTime.now());
            this.courseFeesRepository.save(courseFees);

            return ResponseDto.<CourseFeesDto>builder()
                    .success(true)
                    .message("OK")
                    .data(this.courseFeesMapper.toDto(courseFees))
                    .build();
        } catch (Exception e) {

            return ResponseDto.<CourseFeesDto>builder()
                    .code(-1)
                    .message("courseFee while creating error")
                    .build();
        }
    }

    @Override
    public ResponseDto<CourseFeesDto> get(Integer id) {
        Optional<CourseFees> optional = this.courseFeesRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return ResponseDto.<CourseFeesDto>builder()
                    .code(-1)
                    .message("courseFees is not found")
                    .build();
        }
        var courseFees = optional.get();

        return ResponseDto.<CourseFeesDto>builder()
                .success(true)
                .message("Ok")
                .data(this.courseFeesMapper.toDto(courseFees))
                .build();
    }

    @Override
    public ResponseDto<CourseFeesDto> update(CourseFeesDto dto, Integer id) {
        try {

            Optional<CourseFees> optional = this.courseFeesRepository.findByIdAndDeletedAtIsNull(id);
            if (optional.isEmpty()) {
                return ResponseDto.<CourseFeesDto>builder()
                        .code(-1)
                        .message("CourseFees is not found")
                        .build();
            }
            var courseFees = optional.get();
            courseFees.setUpdatedAt(LocalDateTime.now());
            this.courseFeesMapper.update(courseFees, dto);
            this.courseFeesRepository.save(courseFees);

            return ResponseDto.<CourseFeesDto>builder()
                    .success(true)
                    .message("OK")
                    .data(this.courseFeesMapper.toDto(courseFees))
                    .build();
        } catch (Exception e) {

            return ResponseDto.<CourseFeesDto>builder()
                    .code(-1)
                    .message("CourseFees while updating error")
                    .build();
        }
    }

    @Override
    public ResponseDto<CourseFeesDto> delete(Integer id) {
        var optional = this.courseFeesRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return ResponseDto.<CourseFeesDto>builder()
                    .code(-1)
                    .message("CourseFees is not found")
                    .build();
        }

        var courseFees = optional.get();
        courseFees.setDeletedAt(LocalDateTime.now());
        this.courseFeesRepository.save(courseFees);

        return ResponseDto.<CourseFeesDto>builder()
                .success(true)
                .message("Ok")
                .data(this.courseFeesMapper.toDto(courseFees))
                .build();
    }

    public ResponseDto<List<CourseFeesDto>> getAll() {
        var courseFees = this.courseFeesRepository.findAllByDeletedAtIsNull();
        if (courseFees.isEmpty()) {
            return ResponseDto.<List<CourseFeesDto>>builder()
                    .code(-1)
                    .message("CourseFees are not found")
                    .build();
        }
        return ResponseDto.<List<CourseFeesDto>>builder()
                .success(true)
                .message("OK")
                .data(courseFees.stream().map(this.courseFeesMapper::toDto).toList())
                .build();
    }
}
