package com.company.securityroleconfiguration.service;


import com.company.securityroleconfiguration.dto.FinanceDto;
import com.company.securityroleconfiguration.dto.ResponseDto;
import com.company.securityroleconfiguration.dto.SimpleCrud;
import com.company.securityroleconfiguration.module.Finance;
import com.company.securityroleconfiguration.repository.FinanceRepository;
import com.company.securityroleconfiguration.service.mapper.FinanceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FinanceService implements SimpleCrud<Integer, FinanceDto> {

    private final FinanceRepository financeRepository;
    private final FinanceMapper financeMapper;

    @Override
    public ResponseDto<FinanceDto> create(FinanceDto dto) {

        try {

            Finance finance = this.financeMapper.toEntity(dto);
            finance.setCreatedAt(LocalDateTime.now());
            this.financeRepository.save(finance);

            return ResponseDto.<FinanceDto>builder()
                    .success(true)
                    .message("Ok")
                    .data(this.financeMapper.toDto(finance))
                    .build();
        } catch (Exception e) {

            return ResponseDto.<FinanceDto>builder()
                    .code(-1)
                    .message("finance while creating error")
                    .build();
        }
    }

    @Override
    public ResponseDto<FinanceDto> get(Integer id) {

        Optional<Finance> optional = this.financeRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {

            return ResponseDto.<FinanceDto>builder()
                    .code(-1)
                    .message("finance is not found")
                    .build();
        }
        var finance = optional.get();
        return ResponseDto.<FinanceDto>builder()
                .success(true)
                .message("Ok")
                .data(this.financeMapper.toDto(finance))
                .build();
    }

    @Override
    public ResponseDto<FinanceDto> update(FinanceDto dto, Integer id) {

        try {
            Optional<Finance> optional = this.financeRepository.findByIdAndDeletedAtIsNull(id);
            if (optional.isEmpty()) {
                return ResponseDto.<FinanceDto>builder()
                        .code(-1)
                        .message("finance is not found")
                        .build();
            }
            var finance = optional.get();
            finance.setUpdatedAt(LocalDateTime.now());
            this.financeMapper.update(finance, dto);
            this.financeRepository.save(finance);

            return ResponseDto.<FinanceDto>builder()
                    .success(true)
                    .message("Ok")
                    .data(this.financeMapper.toDto(finance))
                    .build();
        } catch (Exception e) {

            return ResponseDto.<FinanceDto>builder()
                    .code(-1)
                    .message("finance while updating error")
                    .build();
        }
    }

    @Override
    public ResponseDto<FinanceDto> delete(Integer id) {

        Optional<Finance> optional = this.financeRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {

            return ResponseDto.<FinanceDto>builder()
                    .code(-1)
                    .message("finance is not found")
                    .build();
        }
        var finance = optional.get();
        finance.setDeletedAt(LocalDateTime.now());
        this.financeRepository.save(finance);

        return ResponseDto.<FinanceDto>builder()
                .success(true)
                .message("Ok")
                .data(this.financeMapper.toDto(finance))
                .build();
    }

    public ResponseDto<List<FinanceDto>> getAll() {

        List<Finance> finance = this.financeRepository.findAllByDeletedAtIsNull();
        if (finance.isEmpty()) {

            return ResponseDto.<List<FinanceDto>>builder()
                    .code(-1)
                    .message("finances are not found")
                    .build();
        }
        return ResponseDto.<List<FinanceDto>>builder()
                .success(true)
                .message("Ok")
                .data(finance.stream().map(this.financeMapper::toDto).toList())
                .build();
    }
}
