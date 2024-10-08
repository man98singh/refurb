package com.houstondirectauto.refurb.proxy;

import com.houstondirectauto.refurb.UserStatus;
import com.houstondirectauto.refurb.dto.UserDTO;
import com.houstondirectauto.refurb.entity.UserEntity;
import com.houstondirectauto.refurb.exception.EntityNotFoundException;
import com.houstondirectauto.refurb.exception.UnauthorizedException;
import com.houstondirectauto.refurb.filter.JwtTokenUtil;
import com.houstondirectauto.refurb.request.RoleRequest;
import com.houstondirectauto.refurb.response.SignInResponse;
import com.houstondirectauto.refurb.service.UserService;
import com.houstondirectauto.refurb.util.GenerateCodeUtil;
import com.houstondirectauto.refurb.util.MapperUtil;
import com.houstondirectauto.refurb.util.WebUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

import static com.houstondirectauto.refurb.util.Constants.*;

@Component
@Slf4j
public class AuthProxyService {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public AuthProxyService(UserService userService, AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public SignInResponse validateCredentials(String username, String password, String type) throws UsernameNotFoundException, UnauthorizedException, EntityNotFoundException {
        UserEntity user = userService.findByUsername(username);

        if (Objects.nonNull(user) && !user.getUserStatus().equals(UserStatus.ACTIVE)) {
            throw new UnauthorizedException(ACC_NOT_ACT);
        }

        SignInResponse signInResponse = new SignInResponse();

        Boolean twoFactorEnabled = user.getTwoFactorEnabled();
        if (twoFactorEnabled != null && twoFactorEnabled) {
            int code = GenerateCodeUtil.generateCode();

            user.setTwoFactorSecret(code);
            userService.saveUser(user);

            signInResponse.setMessage("2FA code sent to your email.");
            signInResponse.setTwoFactorSecret(code);
            signInResponse.setRequires2FA(true);
            signInResponse.setUser(null);
            signInResponse.setToken(null);

            return signInResponse;
        }

        if (!type.equals(SOCIAL_TYPE)) {
            authenticate(username, password);
        }

        Set<RoleRequest> roles = new HashSet<>();
        if (Objects.nonNull(user.getRoles())) {
            roles = user.getRoles().stream().map(role -> MapperUtil.convertEntityToModel(role, RoleRequest.class)).collect(Collectors.toSet());
        }

        UserDTO userDTO = new UserDTO(user.getId(), user.getUserStatus(),
                user.getEmail(), user.getFirstName(), user.getLastName(), user.getImage(), user.getPhoneNumber(), roles);

        signInResponse.setMessage("Success");
        signInResponse.setRequires2FA(false);
        signInResponse.setUser(userDTO);
        //TODO: We have to create token using JWT
        signInResponse.setToken(WebUtil.encode(user.getEmail()));

        return signInResponse;
    }

    private void authenticate(String username, String password) throws UnauthorizedException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException | BadCredentialsException e) {
            throw new UnauthorizedException(INVALID_CREADTIALS);
        }
    }
}
