package com.houstondirectauto.refurb.controller;

import com.houstondirectauto.refurb.model.UserDTO;
import com.houstondirectauto.refurb.proxy.AuthProxyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import static com.houstondirectauto.refurb.util.Constants.*;

//import java.io.IOException;
//
//import javax.mail.MessagingException;
//import javax.validation.Valid;

import com.houstondirectauto.refurb.exception.EntityNotFoundException;
import com.houstondirectauto.refurb.model.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;

//import com.houstondirectauto.refurb.dto.UserDTO;
import com.houstondirectauto.refurb.exception.BadRequestException;
//import com.houstondirectauto.refurb.exception.EntityNotFoundException;
//import com.houstondirectauto.refurb.exception.UnauthorizedException;
//import com.houstondirectauto.refurb.model.PasswordResetRequest;
//import com.houstondirectauto.refurb.model.User;
//import com.houstondirectauto.refurb.model.UserPermissionResponse;
import com.houstondirectauto.refurb.proxy.UserProxyService;
import com.houstondirectauto.refurb.request.CreateUserRequest;
import com.houstondirectauto.refurb.request.SignInRequest;
//import com.houstondirectauto.refurb.request.SocialSignInRequest;
//import com.houstondirectauto.refurb.request.UpdateImageRequest;

//import freemarker.template.TemplateException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(AUTH)
public class AuthController {
    private final AuthProxyService authProxyService;

    @Autowired
    public AuthController(AuthProxyService authProxyService) {
        this.authProxyService = authProxyService;
    }


    @Operation(summary = "User SignIn", description = "User SignIn for THDA system", tags = { AUTH_TAGS })
    @ApiResponses(value = {
            @ApiResponse(responseCode = STATUS_CODE_OK, description = SUCCESS_MESSAGE, content = @Content(schema = @Schema(implementation = UserDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = STATUS_CODE_BAD_REQUEST, description = BAD_REQUESTS_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")),
            @ApiResponse(responseCode = STATUS_AUTHORISED, description = AUTHORISED_USER_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")),
            @ApiResponse(responseCode = STATUS_NOT_FOUND, description = NOT_FOUND_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")) })
    @RequestMapping(value = USER_SIGNIN, method = RequestMethod.POST)
    public ResponseEntity<UserDTO> createAuthenticationToken(@Valid @RequestBody SignInRequest signInRequest)
            throws Exception {
        return new ResponseEntity<>(authProxyService.getCreateAuthenticationToken(signInRequest.getEmail(),signInRequest.getPassword(),NORMAL_TYPE), HttpStatus.OK);
    }


}
