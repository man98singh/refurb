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
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import static com.houstondirectauto.refurb.util.Constants.*;

@RestController
@RequestMapping("/2fa")
@Validated
public class TwoFactorAuthController {

    private final TwoFactorAuthService twoFactorAuthService;


    @Autowired
    public TwoFactorAuthController(TwoFactorAuthService twoFactorAuthService) {
        this.twoFactorAuthService = twoFactorAuthService;
    }

    @Operation(summary = SUMMARY_2FA_REQUEST, description =DESCRIPTION_2FA_REQUEST)
    @ApiResponses(value = {
            @ApiResponse(responseCode = STATUS_CODE_OK, description =DESCRIPTION_2FA_SENT, content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = STATUS_CODE_BAD_REQUEST, description = DESCRIPTION_BAD_REQUEST, content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = STATUS_NOT_FOUND, description = DESCRIPTION_USER_NOT_FOUND, content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = STATUS_CODE_INTERNAL_ERROR, description = DESCRIPTION_SERVER_ERROR, content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/request")
    public ResponseEntity<String> request2FACode(@Valid @RequestBody Request2FA request) {

        try {
            String generatedCode = twoFactorAuthService.sendCode(request.getUserId());
            return ResponseEntity.ok("2FA code sent: " + generatedCode); // Include the generated code in the response
        } catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(DESCRIPTION_USER_NOT_FOUND);
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(DESCRIPTION_2FA_REQ_SERVER_ERROR);
        }

    }

    @Operation(summary = SUMMARY_2FA_VERIFY, description =DESCRIPTION_2FA_VERIFY )
    @ApiResponses(value = {
            @ApiResponse(responseCode = STATUS_CODE_OK, description =DESCRIPTION_2FA_VERIFIED , content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = STATUS_CODE_BAD_REQUEST, description = DESCRIPTION_BAD_REQUEST, content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = STATUS_NOT_FOUND, description = DESCRIPTION_USER_NOT_FOUND, content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = STATUS_CODE_INTERNAL_ERROR, description = DESCRIPTION_SERVER_ERROR, content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/verify")
    public ResponseEntity<String> verify2FACode(@Valid @RequestBody Verify2FA verifyRequest) {
        try {

            boolean isValid = twoFactorAuthService.verifyCode(verifyRequest.getUserId(), verifyRequest.getCode());
            if (!isValid) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(DESCRIPTION_2FA_MISMATCH);
            }
            return ResponseEntity.ok(DESCRIPTION_2FA_VERIFIED);

        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(DESCRIPTION_USER_NOT_FOUND);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(DESCRIPTION_2FA_VER_SERVER_ERROR);
        }
    }
}
