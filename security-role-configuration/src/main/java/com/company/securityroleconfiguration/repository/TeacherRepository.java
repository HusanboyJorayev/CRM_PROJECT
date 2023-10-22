package com.company.securityroleconfiguration.repository;



import com.company.securityroleconfiguration.module.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Integer> {
    Optional<Teacher>findByTeacherIdAndDeletedAtIsNull(Integer id);
    List<Teacher>findAllByDeletedAtIsNull();
}
