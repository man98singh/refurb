package com.houstondirectauto.refurb.proxy;

import com.houstondirectauto.refurb.UserStatus;
import com.houstondirectauto.refurb.entity.RoleEntity;
import com.houstondirectauto.refurb.entity.UserEntity;
import com.houstondirectauto.refurb.exception.BadRequestException;
import com.houstondirectauto.refurb.exception.EntityNotFoundException;
import com.houstondirectauto.refurb.exception.UnauthorizedException;
import com.houstondirectauto.refurb.filter.JwtTokenUtil;
import com.houstondirectauto.refurb.model.User;
import com.houstondirectauto.refurb.model.UserDTO;
import com.houstondirectauto.refurb.request.CreateUserRequest;
import com.houstondirectauto.refurb.request.RoleRequest;
import com.houstondirectauto.refurb.service.UserService;
import com.houstondirectauto.refurb.util.MapperUtil;
import com.houstondirectauto.refurb.util.WebUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

import static com.houstondirectauto.refurb.util.Constants.*;


@Component
@Slf4j
public class AuthProxyService {

	private final UserService userService;
//	private final MailService mailService;
	private final AuthenticationManager authenticationManager;
	private final JwtTokenUtil jwtTokenUtil;

	/**
	 * Constructor
	 */
//	@Autowired
//	public UserProxyService(UserService userService,MailService mailService,AuthenticationManager authenticationManager,JwtTokenUtil jwtTokenUtil) {
//		this.userService = userService;
//		this.mailService = mailService;
//		this.authenticationManager = authenticationManager;
//		this.jwtTokenUtil = jwtTokenUtil;
//	}

	@Autowired
	public AuthProxyService(UserService userService,AuthenticationManager authenticationManager,JwtTokenUtil jwtTokenUtil) {
		this.userService = userService;
		this.authenticationManager = authenticationManager;
		this.jwtTokenUtil = jwtTokenUtil;
	}

	public UserDTO getCreateAuthenticationToken(String email,String password,String type) throws UsernameNotFoundException, UnauthorizedException, EntityNotFoundException {

	UserEntity user = userService.findByUsername(email);
	if(Objects.nonNull(user) && !user.getUserStatus().equals(UserStatus.ACTIVE)) {
		throw new UnauthorizedException(ACC_NOT_ACT);
	}
	if(!type.equals(SOCIAL_TYPE)) {
		authenticate(email, password);
	}
	final UserDetails userDetails = userService.loadUserByUsername(email);

	UserDTO jwtResponse = new UserDTO();
	jwtResponse.setEmail(user.getEmail());
	jwtResponse.setId(user.getId());
	jwtResponse.setFirstName(user.getFirstName());
	jwtResponse.setLastName(user.getLastName());
	jwtResponse.setPhoneNumber(user.getPhoneNumber());
	jwtResponse.setImage(user.getImage());
	jwtResponse.setLift(user.getLift());
	jwtResponse.setIdmsId(user.getIdmsId());
	jwtResponse.setPinCode(user.getPinCode());
	jwtResponse.setLocation(user.getLocation());
	jwtResponse.setRfIdBadgeId(user.getRfIdBadgeId());
	jwtResponse.setSmartSheetId(user.getSmartSheetId());
//	jwtResponse.setJwtToken(jwtTokenUtil.generateToken(userDetails));
	jwtResponse.setJwtToken(WebUtil.encode(user.getEmail()));
//	jwtResponse.setUserModules(userService.getAllUserModules(user.getId()));
	jwtResponse.setUserStatus(user.getUserStatus());
	if(Objects.nonNull(user.getRoles())){
		jwtResponse.setRoles(user.getRoles().stream().map(role -> MapperUtil.convertEntityToModel(role, RoleRequest.class)).collect(Collectors.toSet()));
	}
	return jwtResponse;
	}

	private void authenticate(String username, String password) throws UnauthorizedException {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new UnauthorizedException(INVALID_CREADTIALS);
		} catch (BadCredentialsException e) {
			throw new UnauthorizedException(INVALID_CREADTIALS);
		}
	}
}
