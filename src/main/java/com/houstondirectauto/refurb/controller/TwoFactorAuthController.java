package com.houstondirectauto.refurb.controller;

import com.houstondirectauto.refurb.exception.EntityNotFoundException;
import com.houstondirectauto.refurb.model.Request2FA;
import com.houstondirectauto.refurb.model.Verify2FA;
import com.houstondirectauto.refurb.service.TwoFactorAuthService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "7. Two Factor Authentication API")
@RequestMapping("/2fa")
public class TwoFactorAuthController {

    private final TwoFactorAuthService twoFactorAuthService;

    @Autowired
    public TwoFactorAuthController(TwoFactorAuthService twoFactorAuthService) {
        this.twoFactorAuthService = twoFactorAuthService;
    }

    @Operation(summary = "Request 2FA Code", description = "Generates and sends a 2FA code to the user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "2FA code sent successfully.", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad request, invalid user ID.", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "User not found.", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error.", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/request")
    public ResponseEntity<String> request2FACode(@RequestBody Request2FA request) {

        String generatedCode = twoFactorAuthService.sendCode(request.getUserId());
        return ResponseEntity.ok("2FA code sent: " + generatedCode); // Include the generated code in the response
    }

    @Operation(summary = "Verify 2FA Code", description = "Verifies the provided 2FA code for the user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "2FA code verification successful.", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad request, invalid user ID or code.", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "User not found.", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error.", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/verify")
    public ResponseEntity<Boolean> verify2FACode(@RequestBody Verify2FA verifyRequest) {
        boolean isValid;
        try {

            isValid = twoFactorAuthService.verifyCode(verifyRequest.getUserId(), verifyRequest.getCode());
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
        return ResponseEntity.ok(isValid);
    }
}
