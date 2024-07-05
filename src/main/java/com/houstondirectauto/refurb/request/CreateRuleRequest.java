package com.houstondirectauto.refurb.request;

import jakarta.validation.constraints.NotBlank;

import com.houstondirectauto.refurb.util.Constants;

import lombok.Data;

@Data
public class CreateRuleRequest {
//	private Integer id;

	private Integer store_id;

	private String name;
	
	@NotBlank(message= Constants.RULE_IS_MANDATORY)
	private String rule;
}
