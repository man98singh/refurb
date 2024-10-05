package com.houstondirectauto.refurb.model;

import java.util.Set;

import com.houstondirectauto.refurb.entity.RoleEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.houstondirectauto.refurb.UserStatus;
import com.houstondirectauto.refurb.request.RoleRequest;
import com.houstondirectauto.refurb.util.Constants;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class User {
	private Integer id;
	private String email;
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
	private UserStatus userStatus;
	private Set<RoleRequest> roles;
	private String twoFaCode;
	private boolean isTwoFaEnabled;
}
