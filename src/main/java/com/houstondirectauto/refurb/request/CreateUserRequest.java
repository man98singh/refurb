package com.houstondirectauto.refurb.request;

import java.util.List;
import java.util.Set;

import com.houstondirectauto.refurb.UserStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import com.houstondirectauto.refurb.util.Constants;

import lombok.Data;

@Data
public class CreateUserRequest {
	
	@Email
	@NotBlank(message= Constants.EMAIL_IS_MANDATORY)
	private String email;
	
	@NotBlank(message= Constants.NAME_IS_MANDATORY)
	private String firstName;
	
	private String lastName;
	
	private String image;
	
	@NotBlank(message= Constants.PASSWORD_IS_MANDATORY)
	private String password;

	@NotBlank(message= Constants.CONTACT_IS_MANDATORY)
	private String phoneNumber;
	private String location;
	private String smartSheetId;
	private String lift;
	private String idmsId;
	private String pinCode;
	private String rfIdBadgeId;
//	private Set<RoleRequest> roles;

	private Integer roleId;

}
