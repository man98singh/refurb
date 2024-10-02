package com.houstondirectauto.refurb.service;

public interface TwoFactorAuthService {
    String sendCode(Integer userId);  // Changed to "username"

     boolean verifyCode(Integer userId, Long code);  // Changed to "username"
}
