package com.houstondirectauto.refurb.model;

import java.util.List;
import java.util.Set;

import com.houstondirectauto.refurb.UserStatus;
//import com.houstondirectauto.refurb.projection.UserModuleProjection;
import com.houstondirectauto.refurb.request.RoleRequest;

import lombok.Data;

@Data
public class UserDTO {
	private  String jwtToken;
	private  UserStatus userStatus;
	private  String email;
	private  String firstName;
	private  String lastName;
	private  String image;
	private  String PhoneNumber;
	private  Integer id;
	private String location;
	private String smartSheetId;
	private String lift;
	private String idmsId;
	private String pinCode;
	private String rfIdBadgeId;
	private Set<RoleRequest> roles;
//	private List<UserModuleProjection> userModules;
//	private  Long roleId;
}
