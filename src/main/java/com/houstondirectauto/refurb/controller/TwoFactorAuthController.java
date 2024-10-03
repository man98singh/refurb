package com.houstondirectauto.refurb.controller;

import com.houstondirectauto.refurb.UserStatus;
import com.houstondirectauto.refurb.entity.UserEntity;
import com.houstondirectauto.refurb.exception.EntityNotFoundException;
import com.houstondirectauto.refurb.exception.UnauthorizedException;
import com.houstondirectauto.refurb.filter.JwtTokenUtil;
import com.houstondirectauto.refurb.model.Request2FA;
import com.houstondirectauto.refurb.model.TwoFactorAuthResponse;
import com.houstondirectauto.refurb.model.UserDTO;
import com.houstondirectauto.refurb.model.Verify2FA;
import com.houstondirectauto.refurb.request.RoleRequest;
import com.houstondirectauto.refurb.service.TwoFactorAuthService;
import com.houstondirectauto.refurb.service.UserService;
import com.houstondirectauto.refurb.util.MapperUtil;
import com.houstondirectauto.refurb.util.WebUtil;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;
import java.util.stream.Collectors;

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
    public ResponseEntity<String> verify2FACode(@Valid @RequestBody Verify2FA verifyRequest) {
        try {

            boolean isValid = twoFactorAuthService.verifyCode(verifyRequest.getUserId(), verifyRequest.getCode());
            if (!isValid) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(DESCRIPTION_2FA_MISMATCH);
            }
            return ResponseEntity.ok(DESCRIPTION_2FA_VERIFIED);

        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(USER_NOT_EXIST);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(DESCRIPTION_2FA_VER_SERVER_ERROR);
        }
    }
}
