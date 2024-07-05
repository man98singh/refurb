package com.houstondirectauto.refurb.controller;

import static com.houstondirectauto.refurb.util.Constants.*;

//import java.io.IOException;
//
//import javax.mail.MessagingException;
//import javax.validation.Valid;

import com.houstondirectauto.refurb.exception.EntityNotFoundException;
import com.houstondirectauto.refurb.model.User;
import com.houstondirectauto.refurb.request.UpdateUserRequest;
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
//import com.houstondirectauto.refurb.request.SignInRequest;
//import com.houstondirectauto.refurb.request.SocialSignInRequest;
//import com.houstondirectauto.refurb.request.UpdateImageRequest;
//import com.houstondirectauto.refurb.request.UpdateUserRequest;

//import freemarker.template.TemplateException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(USERS)
public class UserController {

	private final UserProxyService userProxyService;

	/**
	 * UserController constructor
	 *
	 * @param UserController
	 */
	@Autowired
	public UserController(UserProxyService userProxyService) {
		this.userProxyService = userProxyService;
	}

//	@Operation(summary = "User SignIn", description = "User SignIn for THDA system", tags = { USER_TAGS })
//	@ApiResponses(value = {
//			@ApiResponse(responseCode = STATUS_CODE_OK, description = SUCCESS_MESSAGE, content = @Content(schema = @Schema(implementation = UserDTO.class), mediaType = "application/json")),
//			@ApiResponse(responseCode = STATUS_CODE_BAD_REQUEST, description = BAD_REQUESTS_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")),
//			@ApiResponse(responseCode = STATUS_AUTHORISED, description = AUTHORISED_USER_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")),
//			@ApiResponse(responseCode = STATUS_NOT_FOUND, description = NOT_FOUND_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")) })
//	@RequestMapping(value = USER_SIGNIN, method = RequestMethod.POST)
//	public ResponseEntity<UserDTO> createAuthenticationToken(@Valid @RequestBody SignInRequest signInRequest)
//			throws Exception {
//		return new ResponseEntity<>(userProxyService.getcreateAuthenticationToken(signInRequest.getEmail(),signInRequest.getPassword(),NORMAL_TYPE), HttpStatus.OK);
//	}

//	@Operation(summary = "User SignIn with gmail", description = "User SignIn with gmail for THDA system", tags = { USER_TAGS })
//	@ApiResponses(value = {
//			@ApiResponse(responseCode = STATUS_CODE_OK, description = SUCCESS_MESSAGE, content = @Content(schema = @Schema(implementation = UserDTO.class), mediaType = "application/json")),
//			@ApiResponse(responseCode = STATUS_CODE_BAD_REQUEST, description = BAD_REQUESTS_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")),
//			@ApiResponse(responseCode = STATUS_AUTHORISED, description = AUTHORISED_USER_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")),
//			@ApiResponse(responseCode = STATUS_NOT_FOUND, description = NOT_FOUND_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")) })
//	@RequestMapping(value = USER_SOCIAL_SIGNIN, method = RequestMethod.POST)
//	public ResponseEntity<UserDTO> createAuthenticationToken(@Valid @RequestBody SocialSignInRequest signInRequest)
//			throws Exception {
//		return new ResponseEntity<>(userProxyService.getcreateAuthenticationToken(signInRequest.getEmail(),"", SOCIAL_TYPE), HttpStatus.OK);
//	}

	/*
	 * /** API to Create user
	 *
	 * @param idexxUserToken
	 *
	 * @param user
	 *
	 * @param ip
	 *
	 * @return User
	 *
	 * @throws BadRequestException
	 */
	@Operation(summary = "Create User", description = "Create User "+REFURB, tags = { USER_TAGS })
	@ApiResponses(value = {
			@ApiResponse(responseCode = STATUS_CODE_OK, description = SUCCESS_MESSAGE, content = @Content(schema = @Schema(implementation = CreateUserRequest.class), mediaType = "application/json")),
			@ApiResponse(responseCode = STATUS_CODE_BAD_REQUEST, description = BAD_REQUESTS_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")),
			@ApiResponse(responseCode = STATUS_AUTHORISED, description = AUTHORISED_USER_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")),
			@ApiResponse(responseCode = STATUS_NOT_FOUND, description = NOT_FOUND_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")) })
	@PostMapping
	public ResponseEntity<CreateUserRequest> create(@Valid @RequestBody CreateUserRequest createUserRequest)
			throws BadRequestException {
		return new ResponseEntity<>(userProxyService.createUser(createUserRequest), HttpStatus.CREATED);
	}

	/**
	 * API to Update User
	 *
	 * @param userId
	 * @param user
	 * @param idexxUserToken
	 * @param ip
	 * @return User
	 * @throws EntityNotFoundException
	 */
	@Operation(summary = "Update User", description = "Update User "+REFURB, tags = { USER_TAGS })
	@ApiResponses(value = {
			@ApiResponse(responseCode = STATUS_CODE_OK, description = SUCCESS_MESSAGE, content = @Content(schema = @Schema(implementation = User.class), mediaType = "application/json")),
			@ApiResponse(responseCode = STATUS_CODE_BAD_REQUEST, description = BAD_REQUESTS_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")),
			@ApiResponse(responseCode = STATUS_AUTHORISED, description = AUTHORISED_USER_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")),
			@ApiResponse(responseCode = STATUS_NOT_FOUND, description = NOT_FOUND_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")) })
	@PutMapping(USER_ID)
	public ResponseEntity<UpdateUserRequest> update(@PathVariable(USERS_ID) Integer userId,
			@Valid @RequestBody UpdateUserRequest updateUserRequest) throws EntityNotFoundException {
		return new ResponseEntity<>(userProxyService.updateUser(userId, updateUserRequest), HttpStatus.OK);
	}

//	/**
//	 * API to Update User
//	 *
//	 * @param userId
//	 * @param user
//	 * @param idexxUserToken
//	 * @param ip
//	 * @return User
//	 * @throws EntityNotFoundException
//	 */
//	@Operation(summary = "Update User Image", description = "Update User Image for THDA system", tags = { USER_TAGS })
//	@ApiResponses(value = {
//			@ApiResponse(responseCode = STATUS_CODE_OK, description = SUCCESS_MESSAGE, content = @Content(schema = @Schema(implementation = User.class), mediaType = "application/json")),
//			@ApiResponse(responseCode = STATUS_CODE_BAD_REQUEST, description = BAD_REQUESTS_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")),
//			@ApiResponse(responseCode = STATUS_AUTHORISED, description = AUTHORISED_USER_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")),
//			@ApiResponse(responseCode = STATUS_NOT_FOUND, description = NOT_FOUND_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")) })
//	@PutMapping(IMAGE + USER_ID)
//	public ResponseEntity<User> updateUserImage(@PathVariable(USERS_ID) Integer userId,
//			@Valid @RequestBody UpdateImageRequest payloadData) throws EntityNotFoundException {
//		return new ResponseEntity<>(userProxyService.updateUserImage(userId, payloadData), HttpStatus.OK);
//	}
//
	/**
	 * API to deleteUser
	 *
	 * @param userId
	 * @param idexxUserToken
	 * @param ip
	 * @return
	 * @throws EntityNotFoundException
	 */
	@Operation(summary = "Delete User", description = "Update User for "+REFURB+" system", tags = { USER_TAGS })
	@ApiResponses(value = {
			@ApiResponse(responseCode = STATUS_CODE_OK, description = SUCCESS_MESSAGE, content = @Content(schema = @Schema(implementation = User.class), mediaType = "application/json")),
			@ApiResponse(responseCode = STATUS_CODE_BAD_REQUEST, description = BAD_REQUESTS_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")),
			@ApiResponse(responseCode = STATUS_AUTHORISED, description = AUTHORISED_USER_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")),
			@ApiResponse(responseCode = STATUS_NOT_FOUND, description = NOT_FOUND_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")) })
	@DeleteMapping(USER_ID)
	public ResponseEntity<Void> deleteUser(@PathVariable(USERS_ID) Integer userId) throws EntityNotFoundException {
		userProxyService.deleteUser(userId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/**
	 * API to Retrieve Users
	 *
	 * @param idexxUserToken
	 * @param ip
	 * @return list of User
	 */
	@Operation(summary = "Retrieve User", description = "Retrieve User for "+REFURB+" system", tags = { USER_TAGS })
	@ApiResponses(value = {
			@ApiResponse(responseCode = STATUS_CODE_OK, description = SUCCESS_MESSAGE, content = @Content(schema = @Schema(implementation = User.class), mediaType = "application/json")),
			@ApiResponse(responseCode = STATUS_CODE_BAD_REQUEST, description = BAD_REQUESTS_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")),
			@ApiResponse(responseCode = STATUS_AUTHORISED, description = AUTHORISED_USER_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")),
			@ApiResponse(responseCode = STATUS_NOT_FOUND, description = NOT_FOUND_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")) })
	@GetMapping
	public ResponseEntity<Page<User>> getUsers(
			@RequestParam(value = "pageNo", defaultValue = DEFAULT_PAGE_NUMBER, required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = DEFAULT_SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = DEFAULT_SORT_DIRECTION, required = false) String sortDir,
			@RequestParam(value = PAGEABLE, defaultValue = "true", required = false) boolean pageableStatus) {
		return new ResponseEntity<>(userProxyService.getUsers(pageNo, pageSize, sortBy, sortDir,pageableStatus), HttpStatus.OK);
	}

	/**
	 * API to get user by id
	 *
	 * @param userId
	 * @param idexxUserToken
	 * @param ip
	 * @return User
	 * @throws EntityNotFoundException
	 */
	@Operation(summary = "Get User", description = "Get User for "+REFURB+" system", tags = { USER_TAGS })
	@ApiResponses(value = {
			@ApiResponse(responseCode = STATUS_CODE_OK, description = SUCCESS_MESSAGE, content = @Content(schema = @Schema(implementation = User.class), mediaType = "application/json")),
			@ApiResponse(responseCode = STATUS_CODE_BAD_REQUEST, description = BAD_REQUESTS_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")),
			@ApiResponse(responseCode = STATUS_AUTHORISED, description = AUTHORISED_USER_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")),
			@ApiResponse(responseCode = STATUS_NOT_FOUND, description = NOT_FOUND_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")) })
	@GetMapping(USER_ID)
	public ResponseEntity<User> getUser(@PathVariable(USERS_ID) Integer userId) throws EntityNotFoundException {
		return new ResponseEntity<>(userProxyService.getUserById(userId), HttpStatus.OK);
	}

//	/**
//	 * API to Set user password
//	 *
//	 * @param idexxUserToken
//	 * @param passwordResetRequest
//	 * @param ip
//	 * @return Void
//	 * @throws EntityNotFoundException
//	 * @throws UnauthorizedException
//	 * @throws BadRequestException
//	 */
//	@Operation(summary = "Update User Password", description = "Update User Password for THDA system", tags = {
//			USER_TAGS })
//	@ApiResponses(value = {
//			@ApiResponse(responseCode = STATUS_CODE_OK, description = SUCCESS_MESSAGE, content = @Content(schema = @Schema(implementation = Void.class), mediaType = "application/json")),
//			@ApiResponse(responseCode = STATUS_CODE_BAD_REQUEST, description = BAD_REQUESTS_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")),
//			@ApiResponse(responseCode = STATUS_AUTHORISED, description = AUTHORISED_USER_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")),
//			@ApiResponse(responseCode = STATUS_NOT_FOUND, description = NOT_FOUND_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")) })
//	@PutMapping(UPDATE_CREDENTIALS)
//	public ResponseEntity<Void> updateUserPassword(@Valid @RequestBody PasswordResetRequest passwordResetRequest)
//			throws EntityNotFoundException, BadRequestException {
//		userProxyService.updatePassword(passwordResetRequest.getUserId(), passwordResetRequest.getPassword());
//		return new ResponseEntity<>(HttpStatus.OK);
//	}
//
//	/**
//	 * API to Verify email address
//	 *
//	 * @param idexxUserToken
//	 * @param emailId
//	 * @param ip
//	 * @return Void
//	 * @throws EntityNotFoundException
//	 * @throws BadRequestException
//	 * @throws TemplateException
//	 * @throws MessagingException
//	 * @throws IOException
//	 */
//	@Operation(summary = "Verify User Email", description = "Update User Password for THDA system", tags = {
//			USER_TAGS })
//	@ApiResponses(value = {
//			@ApiResponse(responseCode = STATUS_CODE_OK, description = SUCCESS_MESSAGE, content = @Content(schema = @Schema(implementation = Void.class), mediaType = "application/json")),
//			@ApiResponse(responseCode = STATUS_CODE_BAD_REQUEST, description = BAD_REQUESTS_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")),
//			@ApiResponse(responseCode = STATUS_AUTHORISED, description = AUTHORISED_USER_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")),
//			@ApiResponse(responseCode = STATUS_NOT_FOUND, description = NOT_FOUND_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")) })
//	@PostMapping(VERIFY_USERNAME)
//	public ResponseEntity<Void> verifyEmail(@RequestParam(value = USERNAME) String emailId)
//			throws EntityNotFoundException, BadRequestException, IOException, MessagingException, TemplateException {
//		userProxyService.sendEmail(emailId);
//		return new ResponseEntity<>(HttpStatus.OK);
//	}
//
//	/**
//	 * API to Set user password
//	 *
//	 * @param idexxUserToken
//	 * @param passwordResetRequest
//	 * @param ip
//	 * @return Void
//	 * @throws EntityNotFoundException
//	 * @throws UnauthorizedException
//	 * @throws BadRequestException
//	 */
//	@Operation(summary = "Verify OTP", description = "Verify OTP for THDA system", tags = { USER_TAGS })
//	@ApiResponses(value = {
//			@ApiResponse(responseCode = STATUS_CODE_OK, description = SUCCESS_MESSAGE, content = @Content(schema = @Schema(implementation = Void.class), mediaType = "application/json")),
//			@ApiResponse(responseCode = STATUS_CODE_BAD_REQUEST, description = BAD_REQUESTS_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")),
//			@ApiResponse(responseCode = STATUS_AUTHORISED, description = AUTHORISED_USER_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")),
//			@ApiResponse(responseCode = STATUS_NOT_FOUND, description = NOT_FOUND_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")) })
//	@PutMapping(VERIFY_OTP)
//	public ResponseEntity<User> verifyOtp(@RequestParam(value = "otp") int otp)
//			throws EntityNotFoundException, BadRequestException {
//
//		return new ResponseEntity<>(userProxyService.verifyOtp(otp), HttpStatus.OK);
//	}
//
//	/**
//	 * API to Check Users Already Exists
//	 *
//	 * @param idexxUserToken
//	 * @param emailId
//	 * @param ip
//	 * @return Boolean
//	 */
//	@Operation(summary = "Check Users Already Exists", description = "Check Users Already exist for THDA system", tags = {
//			USER_TAGS })
//	@ApiResponses(value = {
//			@ApiResponse(responseCode = STATUS_CODE_OK, description = SUCCESS_MESSAGE, content = @Content(schema = @Schema(implementation = Boolean.class), mediaType = "application/json")),
//			@ApiResponse(responseCode = STATUS_CODE_BAD_REQUEST, description = BAD_REQUESTS_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")),
//			@ApiResponse(responseCode = STATUS_AUTHORISED, description = AUTHORISED_USER_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")),
//			@ApiResponse(responseCode = STATUS_NOT_FOUND, description = NOT_FOUND_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")) })
//	@GetMapping(FIND_USER_BY_EMAIL)
//	public ResponseEntity<Boolean> isUserExists(@PathVariable(value = EMAIL_ID) String emailId) {
//		return new ResponseEntity<>(userProxyService.isUserExists(emailId), HttpStatus.OK);
//	}

}
