package com.houstondirectauto.refurb.service;

public interface TwoFactorAuthService {
    String sendCode(Integer userId);  // Changed to "username"

     boolean verifyCode(Integer userId, String code);  // Changed to "username"
}
