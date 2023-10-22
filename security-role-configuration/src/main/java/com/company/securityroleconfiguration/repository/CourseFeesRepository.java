package com.company.securityroleconfiguration.repository;


import com.company.securityroleconfiguration.module.CourseFees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseFeesRepository extends JpaRepository<CourseFees,Integer> {

    Optional<CourseFees>findByIdAndDeletedAtIsNull(Integer id);

    List<CourseFees>findAllByDeletedAtIsNull();
}
