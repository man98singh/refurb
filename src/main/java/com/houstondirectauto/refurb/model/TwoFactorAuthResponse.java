package com.houstondirectauto.refurb.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class TwoFactorAuthResponse {
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String generatedCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean success;

}
