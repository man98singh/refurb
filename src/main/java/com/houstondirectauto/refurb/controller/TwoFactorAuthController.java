package com.houstondirectauto.refurb.controller;

import com.houstondirectauto.refurb.model.Request2FA;
import com.houstondirectauto.refurb.model.Verify2FA;
import com.houstondirectauto.refurb.service.TwoFactorAuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/request")
    public ResponseEntity<String> request2FACode(@RequestBody Request2FA request) {
        twoFactorAuthService.sendCode(request.getUsername());
        return ResponseEntity.ok("2FA code sent.");
    }

    @PostMapping("/verify")
    public ResponseEntity<Boolean> verify2FACode(@RequestBody Verify2FA verifyRequest) {
        boolean isValid = twoFactorAuthService.verifyCode(verifyRequest.getUsername(), verifyRequest.getCode());
        return ResponseEntity.ok(isValid);
    }
}
