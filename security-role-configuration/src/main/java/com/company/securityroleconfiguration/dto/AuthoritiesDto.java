package com.company.securityroleconfiguration.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthoritiesDto {

    private Integer id;
    private String username;
    //todo: Role -> USER, ADMIN, SUPER_ADMIN
    private String authority;
    private Integer userId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
