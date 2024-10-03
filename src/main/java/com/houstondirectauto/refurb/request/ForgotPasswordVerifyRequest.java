package com.houstondirectauto.refurb.request;
import static com.houstondirectauto.refurb.util.Constants.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;



@Data
public class ForgotPasswordVerifyRequest {

    @NotBlank(message = EMAIL_REQUIRED)
    private String email;

    @NotBlank(message = PHONE_REQUIRED)
    private String phone;

    @NotBlank(message = CODE_REQUIRED)
    private String Code;
}
