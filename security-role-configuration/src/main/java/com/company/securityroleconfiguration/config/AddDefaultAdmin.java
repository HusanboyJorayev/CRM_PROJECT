package com.company.securityroleconfiguration.config;

import com.company.securityroleconfiguration.module.Authorities;
import com.company.securityroleconfiguration.module.User;
import com.company.securityroleconfiguration.repository.AuthorityRepository;
import com.company.securityroleconfiguration.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class AddDefaultAdmin {


    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    private void addAuthorityAndUser() {

        this.userRepository.findByUsernameAndEnabledIsTrue("admin")
                .ifPresent(this.userRepository::delete);

        this.authorityRepository.findByUsernameAndDeletedAtIsNull("admin")
                .ifPresent(this.authorityRepository::delete);

        User user = this.userRepository.save(
                User.builder()
                        .username("admin")
                        .enabled(true)
                        .createdAt(LocalDateTime.now())
                        .password(passwordEncoder.encode("admin"))
                        .build()
        );

        Authorities role = this.authorityRepository.save(Authorities.builder()
                .authority("ADMIN")
                .userId(user.getId())
                .createdAt(LocalDateTime.now())
                .username(user.getUsername())
                .build()
        );

        log.info("User and Authority added username {}, authority {}", user.getUsername(), role.getAuthority());
    }

}
