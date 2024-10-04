package com.houstondirectauto.refurb.service;

import com.houstondirectauto.refurb.exception.EntityNotFoundException;
import com.houstondirectauto.refurb.entity.UserEntity;
import com.houstondirectauto.refurb.repository.UserRepository;
import com.houstondirectauto.refurb.util.GenerateCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsTwoFactorAuthService implements TwoFactorAuthService {

    @Autowired
    private UserRepository userRepository;

    // Send the 2FA code to the user
    @Override
    public String sendCode(Integer userId) throws EntityNotFoundException {
        // Find user by ID, throw EntityNotFoundException if user doesn't exist
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        // Generate 2FA code
        String code = GenerateCodeUtil.generateCode();
        user.setTwoFaCode(Long.parseLong(code));  // Store the generated code
        userRepository.save(user);  // Save the user entity with updated 2FA code

        // Log and return the generated 2FA code
        System.out.println("2FA code for user " + userId + " is: " + code);
        return code;
    }

    // Verify the 2FA code for the user
    @Override
    public boolean verifyCode(Integer userId, Long code) throws EntityNotFoundException {
        // Find user by ID, throw EntityNotFoundException if user doesn't exist
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        // Check if the provided code matches the stored 2FA code
        if (!code.equals(user.getTwoFaCode())) {
            System.err.println("2FA code mismatch for user " + userId);
            return false;  // Return false if the codes do not match
        }

        // Clear the 2FA code after successful verification
        user.setTwoFaCode(null);
        userRepository.save(user);  // Save the updated user entity

        System.out.println("2FA code verified and cleared for user " + userId);
        return true;  // Return true if the code is valid
    }

    // Generate and Save 2FA code (from UserService, now moved here)
    public void generateAndSave2FACode(UserEntity user) {
        String code = generate2FACode();
        user.setTwoFaCode(Long.parseLong(code));  // Save the generated code to the user entity
        userRepository.save(user);
        System.out.println("Generated 2FA code for user " + user.getEmail() + ": " + code);
    }

    // Helper method to generate a 6-digit 2FA code
    private String generate2FACode() {
        return String.format("%06d", (int) (Math.random() * 1000000));  // Generate a random 6-digit code
    }

    // Verify 2FA code (similar to verifyCode but works with a String input for legacy support)
    public boolean verify2FACode(Integer userId, String inputCode) throws EntityNotFoundException {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Long storedCode = user.getTwoFaCode();  // Get the stored 2FA code

        // Check if the stored code matches the input code
        if (storedCode == null || !storedCode.equals(Long.parseLong(inputCode))) {
            System.err.println("2FA code mismatch for user " + userId);
            return false;
        }

        user.setTwoFaCode(null);  // Clear the 2FA code after successful verification
        userRepository.save(user);  // Save the user entity
        System.out.println("2FA code matched successfully for user " + userId);
        return true;
    }
}
