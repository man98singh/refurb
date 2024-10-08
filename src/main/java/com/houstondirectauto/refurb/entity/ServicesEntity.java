package com.houstondirectauto.refurb.entity;

import com.houstondirectauto.refurb.enums.ServiceEnums;  // Import the enums
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "service")
public class ServicesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;       // No need for @Column unless custom length/behavior is required
    private String description; // Default column behavior will work fine

    private Float price;
    private Integer priority;

    @Enumerated(EnumType.STRING)
    private ServiceEnums.Status status;  // Use the Status enum from ServiceEnums

    private Integer addedBy;

    @Enumerated(EnumType.STRING)
    private ServiceEnums.IsDefaultService isDefaultService;  // Use the IsDefaultService enum from ServiceEnums

    private Integer parent;

    private Float time;

    @Enumerated(EnumType.STRING)
    private ServiceEnums.TimeType timeType;  // Use the TimeType enum from ServiceEnums
}
