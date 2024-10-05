package com.houstondirectauto.refurb.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class Verify2FA {

    @NotNull(message = "User ID cannot be null")
    private Integer userId;

    @NotNull(message = "2FA code cannot be null")
    @Min(value = 100000, message = "2FA code must be at least 6 digits")
    @Max(value = 999999, message = "2FA code must be at most 6 digits")
    private Long code;

}
