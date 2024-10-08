package com.houstondirectauto.refurb.repository;

import com.houstondirectauto.refurb.entity.VehiclesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiclesRepository extends JpaRepository<VehiclesEntity, Long> {

}
