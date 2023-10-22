package com.company.securityroleconfiguration.repository;


import com.company.securityroleconfiguration.module.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Integer> {
    Optional<Subject>findBySubIdAndDeletedAtIsNull(Integer id);

    List<Subject>findAllByDeletedAtIsNull();
}
