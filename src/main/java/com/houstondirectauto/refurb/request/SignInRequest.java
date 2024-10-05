package com.houstondirectauto.refurb.request;

import jakarta.validation.constraints.NotBlank;

import com.houstondirectauto.refurb.util.Constants;

import lombok.Data;

@Data
public class SignInRequest {
	@NotBlank(message= Constants.USERNAME_IS_MANDATORY)
	private String username;
	
	@NotBlank(message= Constants.PASSWORD_IS_MANDATORY)
	private String password;
}
