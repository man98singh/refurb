package com.houstondirectauto.refurb.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;

import com.houstondirectauto.refurb.UserStatus;
import com.houstondirectauto.refurb.request.RoleRequest;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Integer id;
    private UserStatus userStatus;
    private String email;
    private String firstName;
    private String lastName;
    private String image;
    private String phoneNumber;
    private Set<RoleRequest> roles;
}