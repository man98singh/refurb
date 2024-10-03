package com.houstondirectauto.refurb.controller;

import com.houstondirectauto.refurb.exception.EntityNotFoundException;
import com.houstondirectauto.refurb.model.TwoFactorAuthResponse;
import com.houstondirectauto.refurb.request.ForgotPasswordRequest;
import com.houstondirectauto.refurb.entity.UserEntity;
import com.houstondirectauto.refurb.repository.UserRepository;
import com.houstondirectauto.refurb.request.ForgotPasswordVerifyRequest;
import com.houstondirectauto.refurb.service.SmsTwoFactorAuthService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import static com.houstondirectauto.refurb.util.Constants.*;

@RestController
@RequestMapping(AUTH)
public class ForgotPasswordController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SmsTwoFactorAuthService smsTwoFactorAuthService;
    @Operation(summary = FORGOT_PASSWORD_REQUEST_SUMMARY, description = FORGOT_PASSWORD_REQUEST_DESCRIPTION)
    @PostMapping("/request")
    public ResponseEntity<TwoFactorAuthResponse> forgotPasswordRequest(@Valid @RequestBody ForgotPasswordRequest forgotPasswordRequest) {

        TwoFactorAuthResponse response = new TwoFactorAuthResponse();

        try {
            // Use the new method to find the user by both email and phone
            UserEntity user = userRepository.findByEmailAndPhoneNumber(forgotPasswordRequest.getEmail(), forgotPasswordRequest.getPhone())
                    .orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND_BY_EMAIL_AND_PHONE));

            // Generate and send 2FA code via SmsTwoFactorAuthService
            String twoFaCode = smsTwoFactorAuthService.sendCode(user.getId());

            // Prepare response message
            response.setMessage(RESET_INSTRUCTIONS_SENT);
            response.setGeneratedCode(twoFaCode); // This is optional for debugging purposes

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            // Handle the EntityNotFoundException
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
    @Operation(summary = FORGOT_PASSWORD_VERIFY_SUMMARY, description = FORGOT_PASSWORD_VERIFY_DESCRIPTION)
    @PostMapping("/verify")
    public ResponseEntity<TwoFactorAuthResponse> verifyForgotPasswordCode(@Valid @RequestBody ForgotPasswordVerifyRequest verifyRequest) {
        TwoFactorAuthResponse response = new TwoFactorAuthResponse();

        try {
            // Find the user by both email and phone
            UserEntity user = userRepository.findByEmailAndPhoneNumber(verifyRequest.getEmail(), verifyRequest.getPhone())
                    .orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND_BY_EMAIL_AND_PHONE));

            // Verify the 2FA code
            boolean isCodeValid = smsTwoFactorAuthService.verify2FACode(user.getId(), verifyRequest.getCode());

            if (isCodeValid) {
                // Success response if the code is valid
                response.setMessage(VERIFICATION_SUCCESS);
                response.setSuccess(true);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                // Failure response if the code is invalid
                response.setMessage(DESCRIPTION_2FA_MISMATCH);
                response.setSuccess(false);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

        } catch (EntityNotFoundException e) {
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
