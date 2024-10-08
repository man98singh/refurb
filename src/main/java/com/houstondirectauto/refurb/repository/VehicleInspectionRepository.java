package com.houstondirectauto.refurb.repository;

import com.houstondirectauto.refurb.dto.ManagePendingTaskDTO;
import com.houstondirectauto.refurb.entity.VehicleInspectionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleInspectionRepository extends JpaRepository<VehicleInspectionEntity, Integer> {

    // Method with pagination for fetching all records
    @Query("SELECT new com.houstondirectauto.refurb.dto.ManagePendingTaskDTO(v.id, v.title, v.price, v.description, v.image, u.firstName, u.lastName, ve.year, ve.make, ve.model, ve.stockNo, v.createdDate) " +
            "FROM VehicleInspectionEntity v " +
            "JOIN v.vehicle ve " +
            "JOIN UserEntity u ON v.uid = u.id")
    Page<ManagePendingTaskDTO> fetchVehicleInspectionDetails(Pageable pageable);

    // Search by VIN, Stock No, or Service Department ID with pagination
    @Query("SELECT new com.houstondirectauto.refurb.dto.ManagePendingTaskDTO(v.id, v.title, v.price, v.description, v.image, u.firstName, u.lastName, ve.year, ve.make, ve.model, ve.stockNo, v.createdDate) " +
            "FROM VehicleInspectionEntity v " +
            "JOIN v.vehicle ve " +
            "JOIN UserEntity u ON v.uid = u.id " +
            "JOIN DepartmentEntity d ON v.serviceDepartment = d.id " +  // Join with DepartmentEntity using serviceDepartment
            "WHERE (:vin IS NULL OR ve.vin = :vin) " +
            "AND (:stockNo IS NULL OR ve.stockNo = :stockNo) " +
            "AND (:serviceDepartmentId IS NULL OR d.id = :serviceDepartmentId)")  // Search by Service Department ID
    Page<ManagePendingTaskDTO> searchByVinStockNoOrServiceDepartment(
            String vin, String stockNo, Integer serviceDepartmentId, Pageable pageable);
}
