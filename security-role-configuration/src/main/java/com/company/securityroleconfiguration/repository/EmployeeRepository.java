package com.company.securityroleconfiguration.repository;


import com.company.securityroleconfiguration.module.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    Optional<Employee>findByEmpIdAndDeletedAtIsNull(Integer id);
    List<Employee>findAllByDeletedAtIsNull();
}
