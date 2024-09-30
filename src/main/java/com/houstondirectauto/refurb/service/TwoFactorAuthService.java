package com.houstondirectauto.refurb.service;

public interface TwoFactorAuthService {
    void sendCode(String username);  // Changed to "username"

    boolean verifyCode(String username, String code);  // Changed to "username"
}
