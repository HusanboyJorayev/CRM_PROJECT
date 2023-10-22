package com.company.securityroleconfiguration.repository;


import com.company.securityroleconfiguration.module.Finance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FinanceRepository extends JpaRepository<Finance,Integer> {
    Optional<Finance>findByIdAndDeletedAtIsNull(Integer id);

    List<Finance> findAllByDeletedAtIsNull();
}
