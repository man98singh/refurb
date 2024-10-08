package com.houstondirectauto.refurb.entity;

import com.houstondirectauto.refurb.enums.VehicleInspectionEnums;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "vehicle_inspection")
public class VehicleInspectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private VehiclesEntity vehicle;

    private String vin;
    private Integer sid;
    private Integer instance;
    private Integer uid;

    private String title;
    private String serviceStatus;
    private Integer serviceDepartment;
    private Integer servicePriority;
    private Float price;

    @Lob
    private String image;

    @Lob
    private String description;

    @Enumerated(EnumType.STRING)
    private VehicleInspectionEnums.Status status = VehicleInspectionEnums.Status.PENDING;

    @Enumerated(EnumType.STRING)
    private VehicleInspectionEnums.ApprovalStatus approvalStatus = VehicleInspectionEnums.ApprovalStatus.ACTIVE;

    @Lob
    private String note;

    private Integer parent;
    private Integer parentInstance;

    @Enumerated(EnumType.STRING)
    private VehicleInspectionEnums.AddedIn addedIn = VehicleInspectionEnums.AddedIn.APPROVAL;

    private Float time;

    @Enumerated(EnumType.STRING)
    private VehicleInspectionEnums.TimeType timeType;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdDate;

    // Added assignedTo field
    private Integer assignedTo;
}
