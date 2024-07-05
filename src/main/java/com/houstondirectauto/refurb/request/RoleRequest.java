package com.houstondirectauto.refurb.request;

import com.houstondirectauto.refurb.entity.UserRole;

import lombok.Data;

@Data
public class RoleRequest {
	private Long id;
	
	private UserRole roleName;

	private String roleAlias;

}
