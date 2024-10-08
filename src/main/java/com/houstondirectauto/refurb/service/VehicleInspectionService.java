package com.houstondirectauto.refurb.service;

import com.houstondirectauto.refurb.dto.ManagePendingTaskDTO;
import com.houstondirectauto.refurb.repository.VehicleInspectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehicleInspectionService {

    private final VehicleInspectionRepository vehicleInspectionRepository;

    // Fetch all pending tasks with pagination
    public Page<ManagePendingTaskDTO> getVehicleInspectionDetails(Pageable pageable) {
        Page<ManagePendingTaskDTO> pendingTasks = vehicleInspectionRepository.fetchVehicleInspectionDetails(pageable);

        pendingTasks.forEach(task -> {
            if (task.getImage() == null || task.getImage().isEmpty()) {
                task.setImage("https://your-server.com/images/default-image.jpg");
            }
        });

        return pendingTasks;
    }

    // Search by VIN, Stock No, or Service Department ID with pagination
    public Page<ManagePendingTaskDTO> searchByVinStockNoOrServiceDepartment(String vin, String stockNo, Integer serviceDepartmentId, Pageable pageable) {
        return vehicleInspectionRepository.searchByVinStockNoOrServiceDepartment(vin, stockNo, serviceDepartmentId, pageable);
    }

}
