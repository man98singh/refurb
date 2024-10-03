package com.houstondirectauto.refurb.model;

import lombok.Data;

@Data
public class TwoFactorAuthResponse {
    private String message;
    private String generatedCode;
}
