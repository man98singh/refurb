package com.houstondirectauto.refurb.response;

import com.houstondirectauto.refurb.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignInResponse {
    private String message;
    private int twoFactorSecret;
    private boolean requires2FA;
    private UserDTO user;
    private String token;
}
