package com.houstondirectauto.refurb.controller;

import com.houstondirectauto.refurb.exception.EntityNotFoundException;
import com.houstondirectauto.refurb.model.Request2FA;
import com.houstondirectauto.refurb.model.TwoFactorAuthResponse;
import com.houstondirectauto.refurb.model.Verify2FA;
import com.houstondirectauto.refurb.service.TwoFactorAuthService;
import io.swagger.v3.oas.annotations.media.Content;
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
            @ApiResponse(responseCode = STATUS_NOT_FOUND, description = USER_NOT_EXIST, content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = STATUS_CODE_INTERNAL_ERROR, description = DESCRIPTION_SERVER_ERROR, content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/request")
    public ResponseEntity<TwoFactorAuthResponse> request2FACode(@Valid @RequestBody Request2FA request) {
        TwoFactorAuthResponse response = new TwoFactorAuthResponse();
        try {
            String generatedCode = twoFactorAuthService.sendCode(request.getUserId());
            response.setMessage("2FA code sent");
            response.setGeneratedCode(generatedCode);
            return new ResponseEntity<>(response,HttpStatus.OK);
       } catch (EntityNotFoundException e){
            response.setMessage(USER_NOT_EXIST);
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            response.setMessage(DESCRIPTION_2FA_REQ_SERVER_ERROR);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
       }

    }

    @Operation(summary = SUMMARY_2FA_VERIFY, description =DESCRIPTION_2FA_VERIFY )
    @ApiResponses(value = {
            @ApiResponse(responseCode = STATUS_CODE_OK, description =DESCRIPTION_2FA_VERIFIED , content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = STATUS_CODE_BAD_REQUEST, description = DESCRIPTION_BAD_REQUEST, content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = STATUS_NOT_FOUND, description = USERS_NOT_EXIST, content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = STATUS_CODE_INTERNAL_ERROR, description = DESCRIPTION_SERVER_ERROR, content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/verify")
    public ResponseEntity<TwoFactorAuthResponse> verify2FACode(@Valid @RequestBody Verify2FA verifyRequest) {

        TwoFactorAuthResponse response = new TwoFactorAuthResponse();
        try {

            boolean isValid = twoFactorAuthService.verifyCode(verifyRequest.getUserId(), verifyRequest.getCode());
            if (!isValid) {
                response.setMessage(DESCRIPTION_2FA_MISMATCH);
                response.setSuccess(false);
                return  new ResponseEntity<>(response,HttpStatus.BAD_REQUEST );
            }
            response.setMessage(DESCRIPTION_2FA_VERIFIED);
            response.setSuccess(true);
            return new ResponseEntity<>(response,HttpStatus.OK);

        } catch (EntityNotFoundException e) {
            response.setMessage(USERS_NOT_EXIST);
            response.setSuccess(false);
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            response.setMessage(DESCRIPTION_SERVER_ERROR);
            response.setSuccess(false);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
