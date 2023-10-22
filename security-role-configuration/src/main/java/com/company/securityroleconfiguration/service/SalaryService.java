package com.company.securityroleconfiguration.service;


import com.company.securityroleconfiguration.dto.ResponseDto;
import com.company.securityroleconfiguration.dto.SalaryDto;
import com.company.securityroleconfiguration.dto.SimpleCrud;
import com.company.securityroleconfiguration.module.Salary;
import com.company.securityroleconfiguration.repository.SalaryRepository;
import com.company.securityroleconfiguration.service.mapper.SalaryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SalaryService implements SimpleCrud<Integer, SalaryDto> {

    private final SalaryMapper salaryMapper;
    private final SalaryRepository salaryRepository;

    @Override
    public ResponseDto<SalaryDto> create(SalaryDto dto) {
        try {
            Salary salary = this.salaryMapper.toEntity(dto);
            salary.setCreatedAt(LocalDateTime.now());
            this.salaryRepository.save(salary);

            return ResponseDto.<SalaryDto>builder()
                    .success(true)
                    .message("Ok")
                    .data(this.salaryMapper.toDto(salary))
                    .build();
        } catch (Exception e) {

            return ResponseDto.<SalaryDto>builder()
                    .code(-1)
                    .message("salary while creating error")
                    .build();
        }
    }

    @Override
    public ResponseDto<SalaryDto> get(Integer id) {
        Optional<Salary> optional = this.salaryRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {

            return ResponseDto.<SalaryDto>builder()
                    .code(-1)
                    .message("salary is not found")
                    .build();
        }
        Salary salary = optional.get();
        return ResponseDto.<SalaryDto>builder()
                .success(true)
                .message("Ok")
                .data(this.salaryMapper.toDto(salary))
                .build();
    }

    @Override
    public ResponseDto<SalaryDto> update(SalaryDto dto, Integer id) {
        try {
            Optional<Salary> optional = this.salaryRepository.findByIdAndDeletedAtIsNull(id);
            if (optional.isEmpty()) {
                return ResponseDto.<SalaryDto>builder()
                        .code(-1)
                        .message("salary is not found")
                        .build();
            }
            Salary salary = optional.get();
            salary.setUpdatedAt(LocalDateTime.now());
            this.salaryMapper.update(salary, dto);
            this.salaryRepository.save(salary);

            return ResponseDto.<SalaryDto>builder()
                    .success(true)
                    .message("Ok")
                    .data(this.salaryMapper.toDto(salary))
                    .build();
        } catch (Exception e) {

            return ResponseDto.<SalaryDto>builder()
                    .code(-1)
                    .message("salary while updating error")
                    .build();
        }
    }

    @Override
    public ResponseDto<SalaryDto> delete(Integer id) {

        Optional<Salary> optional = this.salaryRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {

            return ResponseDto.<SalaryDto>builder()
                    .code(-1)
                    .message("salary is not found")
                    .build();
        }

        Salary salary = optional.get();
        salary.setDeletedAt(LocalDateTime.now());
        this.salaryRepository.save(salary);

        return ResponseDto.<SalaryDto>builder()
                .success(true)
                .message("Ok")
                .data(this.salaryMapper.toDto(salary))
                .build();
    }

    public ResponseDto<List<SalaryDto>> getAll() {
        List<Salary> salary = this.salaryRepository.findAllByDeletedAtIsNull();
        if (salary.isEmpty()) {

            return ResponseDto.<List<SalaryDto>>builder()
                    .code(-1)
                    .message("Salaries are not found")
                    .build();
        }
        return ResponseDto.<List<SalaryDto>>builder()
                .success(true)
                .message("Ok")
                .data(salary.stream().map(this.salaryMapper::toDto).toList())
                .build();
    }
}
