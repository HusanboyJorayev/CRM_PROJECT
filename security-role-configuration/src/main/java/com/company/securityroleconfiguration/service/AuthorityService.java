package com.company.securityroleconfiguration.service;

import com.company.securityroleconfiguration.dto.AuthoritiesDto;
import com.company.securityroleconfiguration.dto.ResponseDto;
import com.company.securityroleconfiguration.dto.SimpleCrud;
import com.company.securityroleconfiguration.dto.request.RequestAuthDto;
import com.company.securityroleconfiguration.module.Authorities;
import com.company.securityroleconfiguration.repository.AuthorityRepository;
import com.company.securityroleconfiguration.service.mapper.AuthMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorityService implements SimpleCrud<Integer, AuthoritiesDto> {

    private final AuthMapper authMapper;
    private final AuthorityRepository authorityRepository;


    @Override
    public ResponseDto<AuthoritiesDto> create(AuthoritiesDto dto) {
        try {
            Authorities role = this.authMapper.toEntity(dto);
            role.setCreatedAt(LocalDateTime.now());
            this.authorityRepository.save(role);

            return ResponseDto.<AuthoritiesDto>builder()
                    .success(true)
                    .message("Ok")
                    .data(this.authMapper.toDto(role))
                    .build();

        } catch (Exception e) {

            return ResponseDto.<AuthoritiesDto>builder()
                    .code(-1)
                    .message("Role while saving error")
                    .build();
        }
    }

    @Override
    public ResponseDto<AuthoritiesDto> get(Integer id) {

        Optional<Authorities> optional = this.authorityRepository.findByUserIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return ResponseDto.<AuthoritiesDto>builder()
                    .code(-1)
                    .message("Role is not found")
                    .build();
        }
        Authorities role = optional.get();
        return ResponseDto.<AuthoritiesDto>builder()
                .success(true)
                .message("Ok")
                .data(this.authMapper.toDto(role))
                .build();
    }

    @Override
    public ResponseDto<AuthoritiesDto> update(AuthoritiesDto dto, Integer id) {
        try {
            Optional<Authorities> optional = this.authorityRepository.findByUserIdAndDeletedAtIsNull(id);
            if (optional.isEmpty()) {
                return ResponseDto.<AuthoritiesDto>builder()
                        .code(-1)
                        .message("Role is not found")
                        .build();
            }
            Authorities authorities = optional.get();
            authorities.setUpdatedAt(LocalDateTime.now());
            this.authMapper.update(authorities, dto);
            this.authorityRepository.save(authorities);

            return ResponseDto.<AuthoritiesDto>builder()
                    .success(true)
                    .message("Ok")
                    .data(this.authMapper.toDto(authorities))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<AuthoritiesDto>builder()
                    .code(-1)
                    .message("Role while saving error")
                    .build();

        }
    }

    @Override
    public ResponseDto<AuthoritiesDto> delete(Integer id) {
        Optional<Authorities> optional = this.authorityRepository.findByUserIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return ResponseDto.<AuthoritiesDto>builder()
                    .code(-1)
                    .message("Role is not found")
                    .build();
        }
        Authorities role = optional.get();
        role.setUpdatedAt(LocalDateTime.now());
        this.authorityRepository.save(role);
        return ResponseDto.<AuthoritiesDto>builder()
                .success(true)
                .message("Ok")
                .data(this.authMapper.toDto(role))
                .build();
    }

}
