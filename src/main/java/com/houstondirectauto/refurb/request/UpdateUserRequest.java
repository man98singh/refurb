package com.houstondirectauto.refurb.request;

import java.util.List;
import java.util.Set;

import com.houstondirectauto.refurb.UserStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import com.houstondirectauto.refurb.util.Constants;

import lombok.Data;

@Data
public class UpdateUserRequest {

	private String firstName;

	private String lastName;

	private String image;
	private String phoneNumber;
	private String location;
	private String smartSheetId;
	private String lift;
	private String idmsId;
	private String pinCode;
	private String rfIdBadgeId;
	private Integer roleId;
	
}
