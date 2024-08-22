package com.houstondirectauto.refurb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.houstondirectauto.refurb.proxy.RoleProxyService;
import com.houstondirectauto.refurb.request.RoleRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import static com.houstondirectauto.refurb.util.Constants.*;

@RestController
@RequestMapping(ROLE)
public class RoleController {

	private final RoleProxyService roleProxyService;

	/**
	 * Controller constructor
	 * 
	 * @param Controller
	 */
	@Autowired
	public RoleController(RoleProxyService roleProxyService) {
		this.roleProxyService = roleProxyService;
	}

//	/**
//	 * @param rule
//	 * @return
//	 * @throws BadRequestException
//	 */
//	@Operation(summary = "Create Module", description = "Create Module for THDA system", security = @SecurityRequirement(name = BEARER_AUTH), tags = {
//			ROLE_TAGS })
//	@ApiResponses(value = {
//			@ApiResponse(responseCode = STATUS_CODE_OK, description = SUCCESS_MESSAGE, content = @Content(schema = @Schema(implementation = Rule.class), mediaType = "application/json")),
//			@ApiResponse(responseCode = STATUS_CODE_BAD_REQUEST, description = BAD_REQUESTS_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")),
//			@ApiResponse(responseCode = STATUS_AUTHORISED, description = AUTHORISED_USER_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")),
//			@ApiResponse(responseCode = STATUS_NOT_FOUND, description = NOT_FOUND_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")) })
//	@PostMapping
//	public ResponseEntity<ResponseWrapper> create(@Valid @RequestBody Modules payloadData) throws BadRequestException {
//		moduleProxyService.create(payloadData);
//		return new ResponseEntity<>(new ResponseWrapper(INSERT_SUCCESS), HttpStatus.CREATED);
//	}
//	
	
	/**
	 * API to Retrieve
	 * 
	 * @param 
	 * @param ip
	 * @return list of User
	 */
	@Operation(summary = "Retrieve role", description = "Retrieve roles for  "+REFURB+"  system",  tags = {
			ROLE_TAGS })
	@ApiResponses(value = {
			@ApiResponse(responseCode = STATUS_CODE_OK, description = SUCCESS_MESSAGE, content = @Content(schema = @Schema(implementation = RoleRequest.class), mediaType = "application/json")),
			@ApiResponse(responseCode = STATUS_CODE_BAD_REQUEST, description = BAD_REQUESTS_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")),
			@ApiResponse(responseCode = STATUS_AUTHORISED, description = AUTHORISED_USER_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")),
			@ApiResponse(responseCode = STATUS_NOT_FOUND, description = NOT_FOUND_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")) })
	@GetMapping
	public ResponseEntity<Page<RoleRequest>> getAll(
			@RequestParam(value = PAGE_NUMBER_KEY, defaultValue = DEFAULT_PAGE_NUMBER, required = false) int pageNo,
			@RequestParam(value = PAGE_SIZE_KEY, defaultValue = DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = SORT_BY_KEY, defaultValue = DEFAULT_SORT_BY, required = false) String sortBy,
			@RequestParam(value = SORT_DIRECTION_KEY, defaultValue = DEFAULT_SORT_DIRECTION, required = false) String sortDir,
			@RequestParam(value = PAGEABLE, defaultValue = "true", required = false) boolean pageableStatus
			) {
		return new ResponseEntity<>(roleProxyService.getAll(pageNo, pageSize, sortBy, sortDir,pageableStatus), HttpStatus.OK);
	}

	
		

	
}
