package com.houstondirectauto.refurb.model;

import com.houstondirectauto.refurb.UserStatus;
import com.houstondirectauto.refurb.request.RoleRequest;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.Set;

@Data
public class Vendor {
	private Integer id;
	private String email;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String mobileNumber;
	private String city;
	private String state;
	private String country;
	private String fax;
	private String title;;
}
