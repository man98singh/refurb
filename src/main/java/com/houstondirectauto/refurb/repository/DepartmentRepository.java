package com.houstondirectauto.refurb.repository;

import com.houstondirectauto.refurb.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Integer> {
}
