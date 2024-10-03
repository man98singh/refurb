package com.houstondirectauto.refurb.request;
import static com.houstondirectauto.refurb.util.Constants.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;




@Data
public class ForgotPasswordRequest {

    @Email(message = INVALID_EMAIL_FORMAT)
    @NotBlank(message = EMAIL_REQUIRED)
    private String email;

    @NotBlank(message = PHONE_REQUIRED)
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = INVALID_PHONE_FORMAT)
    private String phoneNumber;
}