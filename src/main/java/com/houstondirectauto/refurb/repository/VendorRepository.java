package com.houstondirectauto.refurb.repository;

import com.houstondirectauto.refurb.entity.VendorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends JpaRepository<VendorEntity, Integer> {
}
