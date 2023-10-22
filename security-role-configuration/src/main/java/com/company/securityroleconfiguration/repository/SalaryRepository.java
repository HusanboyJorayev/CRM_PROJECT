package com.company.securityroleconfiguration.repository;


import com.company.securityroleconfiguration.module.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SalaryRepository extends JpaRepository<Salary,Integer> {

    Optional<Salary>findByIdAndDeletedAtIsNull(Integer id);
    List<Salary>findAllByDeletedAtIsNull();
}
