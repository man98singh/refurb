package com.houstondirectauto.refurb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ManagePendingTaskDTO {
    private Integer id;
    private String title;
    private Float price;
    private String description;
    private String image;
    private String firstName; // Added field for user's first name
    private String lastName;  // Added field for user's last name
    private String year;
    private String make;
    private String model;
    private String stockNo;
    private Date createdDate;

    // Override the getter for the image field to return a full URL
    public String getImage() {
        if (this.image == null || this.image.isEmpty()) {
            return "";  // Return empty string if no image
        }
        return "https://your-server.com/images/" + this.image;  // Replace with your actual base URL
    }
}
