package com.houstondirectauto.refurb.entity;

import com.houstondirectauto.refurb.enums.VehicleStatus;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class VehiclesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vin;
    private String year;
    private String make;
    private String model;
    private String stockNo;
    private String color;

    private Float price;
    private Integer mileage;

    private Integer assignedTo;
    private String idmsStatus;
    private String acquiredFromName;
    private String acquiredByName;

    @Temporal(TemporalType.TIMESTAMP)
    private Date acquiredDate;

    private String titleLocationDesc;
    private String titleStatusDesc;
    private Integer daysLot;

    @Lob
    private String additionalNotes;
    private Integer noOfKey;

    @Temporal(TemporalType.TIMESTAMP)
    private Date depAssignedDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date assignedDate;

    private Integer currentDepartment;

    @Enumerated(EnumType.STRING)
    private VehicleStatus vehicleStatus;

    // One-to-Many relationship with VehicleInspectionEntity
    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<VehicleInspectionEntity> inspections;
}
