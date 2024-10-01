package com.houstondirectauto.refurb.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class SmsTwoFactorAuthService implements TwoFactorAuthService {

    private final Map<String, String> codeStorage = new HashMap<>();

    @Override
    public void sendCode(String username) {
        String code = generateCode();
        codeStorage.put(username, code);
        System.out.println("2FA code for user " + username + " is: " + code);
    }

    @Override
    public boolean verifyCode(String username, String code) {
        String storedCode = codeStorage.get(username);
        return storedCode != null && storedCode.equals(code);
    }


    private String generateCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }
}
