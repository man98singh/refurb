package com.houstondirectauto.refurb.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Request2FA {
    @NotNull(message = "USER ID cannot be null")
    private Integer userId;  // Change this to Integer for user ID



}
