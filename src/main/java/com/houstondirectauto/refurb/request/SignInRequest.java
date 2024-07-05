package com.houstondirectauto.refurb.request;

import jakarta.validation.constraints.NotBlank;

import com.houstondirectauto.refurb.util.Constants;

import lombok.Data;

@Data
public class SignInRequest {
	@NotBlank(message= Constants.EMAIL_IS_MANDATORY)
	private String email;
	
	@NotBlank(message= Constants.PASSWORD_IS_MANDATORY)
	private String password;
}
