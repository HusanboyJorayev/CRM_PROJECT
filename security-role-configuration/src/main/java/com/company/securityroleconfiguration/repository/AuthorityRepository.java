package com.company.securityroleconfiguration.repository;

import com.company.securityroleconfiguration.module.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends JpaRepository<Authorities, Integer> {
    Optional<Authorities> findByUsernameAndDeletedAtIsNull(String username);
    Optional<Authorities>findByUserIdAndDeletedAtIsNull(Integer id);

}
