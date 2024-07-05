package com.houstondirectauto.refurb.request;



import jakarta.validation.constraints.NotBlank;

import com.houstondirectauto.refurb.util.Constants;

import lombok.Data;

@Data
public class UserImageRequest {
	@NotBlank(message= Constants.NAME_IS_MANDATORY)
	private String fileName;
}
