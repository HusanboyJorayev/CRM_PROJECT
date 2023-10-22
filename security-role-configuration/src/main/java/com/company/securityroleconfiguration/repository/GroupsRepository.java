package com.company.securityroleconfiguration.repository;


import com.company.securityroleconfiguration.module.Groups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupsRepository extends JpaRepository<Groups,Integer> {
    Optional<Groups>findByGroupIdAndDeletedAtIsNull(Integer id);

    List<Groups>findAllByDeletedAtIsNull();
}
