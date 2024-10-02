
package com.houstondirectauto.refurb.service;
import com.houstondirectauto.refurb.exception.EntityNotFoundException; // Add this line

import com.houstondirectauto.refurb.entity.UserEntity; // Import UserEntity
import com.houstondirectauto.refurb.exception.EntityNotFoundException; // Ensure this import is present
import com.houstondirectauto.refurb.repository.UserRepository; // Import UserRepository
import com.houstondirectauto.refurb.util.GenerateCodeUtil;
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
            String code = GenerateCodeUtil.generateCode();
            UserEntity user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found")); // Handle user not found
            user.setTwoFaCode(Long.parseLong(code));
            userRepository.save(user);
            System.out.println("2FA code for user " + userId + " is: " + code);
            return code;
        } catch (EntityNotFoundException e) {
            System.err.println("Error: " + e.getMessage()); // Log the error
            return "User doesn't exist"; // Return message
        }
    }

    @Override
    public boolean verifyCode(Integer userId, Long code) {
        try {
            UserEntity user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
            if (!code.equals(user.getTwoFaCode())) {
                System.err.println("Code mismatch"); // Log the error
                return false; // Return false
            }
            user.setTwoFaCode(null);  //setting to null in db
            userRepository.save(user);

            System.out.println("2FA code verified and cleared for user " + userId);
            return true;
        } catch (EntityNotFoundException e) {
            System.err.println("Error: " + e.getMessage()); // Log the error
            return false; // Return false
        }
    }





}
