package com.houstondirectauto.refurb.service;

import com.houstondirectauto.refurb.exception.EntityNotFoundException;

public interface TwoFactorAuthService {
    String sendCode(Integer userId) throws EntityNotFoundException;  // Changed to "username"

     boolean verifyCode(Integer userId, Long code) throws EntityNotFoundException;  // Changed to "username"
}
