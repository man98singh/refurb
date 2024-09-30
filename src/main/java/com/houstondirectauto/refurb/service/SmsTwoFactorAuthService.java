package com.houstondirectauto.refurb.service;

import org.springframework.stereotype.Service;

@Service
public class SmsTwoFactorAuthService implements TwoFactorAuthService {
    @Override
    public void sendCode(String userIdentifier) {
        // Logic to send SMS code (use an SMS provider API)
    }

    @Override
    public boolean verifyCode(String userIdentifier, String code) {
        // Logic to verify the SMS code
        return true; // Replace with actual verification logic
    }
}
