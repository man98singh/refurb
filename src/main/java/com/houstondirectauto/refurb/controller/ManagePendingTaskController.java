package com.houstondirectauto.refurb.controller;

import com.houstondirectauto.refurb.dto.ManagePendingTaskDTO;
import com.houstondirectauto.refurb.service.VehicleInspectionService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/managependingtask")
public class ManagePendingTaskController {

    private final VehicleInspectionService vehicleInspectionService;

    public ManagePendingTaskController(VehicleInspectionService vehicleInspectionService) {
        this.vehicleInspectionService = vehicleInspectionService;
    }

    // fetching pending tasks with pagination and sorting
    @GetMapping
    public ResponseEntity<Page<ManagePendingTaskDTO>> getPendingTasks(
            @PageableDefault(size = 10, sort = "title", direction = Sort.Direction.ASC) Pageable pageable) { // Default size and sorting set here
        Page<ManagePendingTaskDTO> pendingTasks = vehicleInspectionService.getVehicleInspectionDetails(pageable);

        // Logging the result for debugging
        System.out.println("Pending Tasks: " + pendingTasks.getContent());

        return ResponseEntity.ok(pendingTasks);
    }

    // Search by VIN or Stock No with pagination and sorting
    @GetMapping("/search") // under development
    public ResponseEntity<Page<ManagePendingTaskDTO>> searchByVinStockNo(
            @RequestParam(required = false) String query,  // only one  input parameter
            @RequestParam(required = false) Integer serviceDepartmentId,  // Service Department ID
            @PageableDefault(size = 10, sort = "title", direction = Sort.Direction.ASC) Pageable pageable) {

        // Split the query parameter into VIN and Stock No
        String vin = (query != null && query.length() == 17) ? query : null; // assuming VIN must be 17 characters
        String stockNo = (query != null && query.length() != 17) ? query : null; // if not VIN, treat as Stock No

        // Logging input parameters for debugging
        System.out.println("VIN: " + vin + " StockNo: " + stockNo + " Service Department ID: " + serviceDepartmentId);

        // Call the correct service method
        Page<ManagePendingTaskDTO> result = vehicleInspectionService.searchByVinStockNoOrServiceDepartment(vin, stockNo, serviceDepartmentId, pageable);

        // Logging the service result for debugging
        System.out.println("Service Result: " + result.getContent());

        return ResponseEntity.ok(result);
    }
}
