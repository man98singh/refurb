package com.houstondirectauto.refurb.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import static com.houstondirectauto.refurb.util.Constants.*;

import com.houstondirectauto.refurb.request.SignInRequest;
import com.houstondirectauto.refurb.dto.UserDTO;
import com.houstondirectauto.refurb.proxy.AuthProxyService;

@RestController
@RequestMapping(AUTH)
public class AuthController {
    private final AuthProxyService authProxyService;

    @Autowired
    public AuthController(AuthProxyService authProxyService) {
        this.authProxyService = authProxyService;
    }

    @Operation(summary = "User SignIn", description = "User SignIn for refurb system", tags = {AUTH_TAGS})
    @ApiResponses(value = {
            @ApiResponse(responseCode = STATUS_CODE_OK, description = SUCCESS_MESSAGE, content = @Content(schema = @Schema(implementation = UserDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = STATUS_CODE_BAD_REQUEST, description = BAD_REQUESTS_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")),
            @ApiResponse(responseCode = STATUS_AUTHORISED, description = AUTHORISED_USER_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")),
            @ApiResponse(responseCode = STATUS_NOT_FOUND, description = NOT_FOUND_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json"))})
    @RequestMapping(value = USER_SIGNIN, method = RequestMethod.POST)
    public ResponseEntity<?> signIn(@Valid @RequestBody SignInRequest signInRequest)
            throws Exception {
        return new ResponseEntity<>(
                authProxyService.validateCredentials(signInRequest.getUsername(), signInRequest.getPassword(), NORMAL_TYPE),
                HttpStatus.OK);
    }
}
