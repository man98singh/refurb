
package com.houstondirectauto.refurb.service;
import com.houstondirectauto.refurb.exception.EntityNotFoundException; // Add this line

import com.houstondirectauto.refurb.entity.UserEntity; // Import UserEntity
import com.houstondirectauto.refurb.exception.EntityNotFoundException; // Ensure this import is present
import com.houstondirectauto.refurb.repository.UserRepository; // Import UserRepository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Random;

@Service
public class SmsTwoFactorAuthService implements TwoFactorAuthService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public String sendCode(Integer userId) {
        try {
            String code = generateCode();
            UserEntity user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found")); // Handle user not found
            user.setTwoFaCode(code);
            user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            userRepository.save(user);
            System.out.println("2FA code for user " + userId + " is: " + code);
            return code;
        } catch (EntityNotFoundException e) {
            System.err.println("Error: " + e.getMessage()); // Log the error
            return "User doesn't exist"; // Return message
        }
    }

    @Override
    public boolean verifyCode(Integer userId, String code) {
        try {
            UserEntity user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
            if (!code.equals(user.getTwoFaCode())) {
                System.err.println("Code mismatch"); // Log the error
                return false; // Return false
            }
            return true;
        } catch (EntityNotFoundException e) {
            System.err.println("Error: " + e.getMessage()); // Log the error
            return false; // Return false
        }
    }




    private String generateCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }
}
