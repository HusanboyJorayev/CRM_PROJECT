package com.company.securityroleconfiguration.service;

import com.company.securityroleconfiguration.dto.ResponseDto;
import com.company.securityroleconfiguration.dto.SimpleCrud;
import com.company.securityroleconfiguration.dto.SubjectDto;
import com.company.securityroleconfiguration.repository.SubjectRepository;
import com.company.securityroleconfiguration.service.mapper.SubjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService implements SimpleCrud<Integer, SubjectDto> {

    private final SubjectMapper subjectMapper;
    private final SubjectRepository subjectRepository;


    @Override
    public ResponseDto<SubjectDto> create(SubjectDto dto) {

        try {

            var subject = this.subjectMapper.toEntity(dto);
            subject.setCreatedAt(LocalDateTime.now());
            this.subjectRepository.save(subject);

            return ResponseDto.<SubjectDto>builder()
                    .success(true)
                    .message("Ok")
                    .data(this.subjectMapper.toDto(subject))
                    .build();
        } catch (Exception e) {

            return ResponseDto.<SubjectDto>builder()
                    .code(-1)
                    .message("Subject while createing error")
                    .build();
        }
    }

    @Override
    public ResponseDto<SubjectDto> get(Integer id) {

        var optional = this.subjectRepository.findBySubIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {

            return ResponseDto.<SubjectDto>builder()
                    .code(-1)
                    .message("subject is not found")
                    .build();
        }
        var subject = optional.get();
        return ResponseDto.<SubjectDto>builder()
                .success(true)
                .message("Ok")
                .data(this.subjectMapper.toDto(subject))
                .build();
    }

    @Override
    public ResponseDto<SubjectDto> update(SubjectDto dto, Integer id) {

        try {

            var optional = this.subjectRepository.findBySubIdAndDeletedAtIsNull(id);
            if (optional.isEmpty()) {
                return ResponseDto.<SubjectDto>builder()
                        .code(-1)
                        .message("subject is not found")
                        .build();
            }
            var subject = optional.get();
            subject.setUpdatedAt(LocalDateTime.now());
            this.subjectMapper.update(subject, dto);
            this.subjectRepository.save(subject);

            return ResponseDto.<SubjectDto>builder()
                    .success(true)
                    .message("Ok")
                    .data(this.subjectMapper.toDto(subject))
                    .build();
        } catch (Exception e) {

            return ResponseDto.<SubjectDto>builder()
                    .code(-1)
                    .message("subject while updating error")
                    .build();
        }
    }

    @Override
    public ResponseDto<SubjectDto> delete(Integer id) {

        var optional = this.subjectRepository.findBySubIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {

            return ResponseDto.<SubjectDto>builder()
                    .code(-1)
                    .message("subject is not found")
                    .build();
        }
        var subject = optional.get();
        subject.setDeletedAt(LocalDateTime.now());
        this.subjectRepository.save(subject);

        return ResponseDto.<SubjectDto>builder()
                .success(true)
                .message("Ok")
                .data(this.subjectMapper.toDto(subject))
                .build();
    }

    public ResponseDto<List<SubjectDto>> getAll() {

        var subject = this.subjectRepository.findAllByDeletedAtIsNull();
        if (subject.isEmpty()) {

            return ResponseDto.<List<SubjectDto>>builder()
                    .code(-1)
                    .message("subjects are not found")
                    .build();
        }

        return ResponseDto.<List<SubjectDto>>builder()
                .success(true)
                .message("Ok")
                .data(subject.stream().map(this.subjectMapper::toDto).toList())
                .build();
    }
}
