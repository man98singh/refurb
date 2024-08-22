package com.houstondirectauto.refurb.controller;

import com.houstondirectauto.refurb.exception.BadRequestException;
import com.houstondirectauto.refurb.exception.EntityNotFoundException;
import com.houstondirectauto.refurb.model.Department;
import com.houstondirectauto.refurb.proxy.DepartmentProxyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.houstondirectauto.refurb.util.Constants.*;

@RestController
@RequestMapping(DEPARTMENT)
public class DepartmentController {

	private final DepartmentProxyService departmentProxyService;

	/**
	 * constructor
	 *
	 * @param DepartmentController
	 */
	@Autowired
	public DepartmentController(DepartmentProxyService departmentProxyService) {
		this.departmentProxyService = departmentProxyService;
	}


	@Operation(summary = "Create Department", description = "Create Department "+REFURB, security = @SecurityRequirement(name = BEARER_AUTH), tags = { DEPARTMENT_TAGS })
	@ApiResponses(value = {
			@ApiResponse(responseCode = STATUS_CODE_OK, description = SUCCESS_MESSAGE, content = @Content(schema = @Schema(implementation = Department.class), mediaType = "application/json")),
			@ApiResponse(responseCode = STATUS_CODE_BAD_REQUEST, description = BAD_REQUESTS_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")),
			@ApiResponse(responseCode = STATUS_AUTHORISED, description = AUTHORISED_USER_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")),
			@ApiResponse(responseCode = STATUS_NOT_FOUND, description = NOT_FOUND_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")) })
	@PostMapping
	public ResponseEntity<Department> create(@Valid @RequestBody Department dto)
			throws BadRequestException {
		return new ResponseEntity<>(departmentProxyService.create(dto), HttpStatus.CREATED);
	}

	@Operation(summary = "Update Department", description = "Update Department "+REFURB,security = @SecurityRequirement(name = BEARER_AUTH), tags = { DEPARTMENT_TAGS })
	@ApiResponses(value = {
			@ApiResponse(responseCode = STATUS_CODE_OK, description = SUCCESS_MESSAGE, content = @Content(schema = @Schema(implementation = Department.class), mediaType = "application/json")),
			@ApiResponse(responseCode = STATUS_CODE_BAD_REQUEST, description = BAD_REQUESTS_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")),
			@ApiResponse(responseCode = STATUS_AUTHORISED, description = AUTHORISED_USER_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")),
			@ApiResponse(responseCode = STATUS_NOT_FOUND, description = NOT_FOUND_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")) })
	@PutMapping(DEPARTMENT_ID)
	public ResponseEntity<Department> update(@PathVariable(DEPARTMENTS_ID) Integer id,
													@Valid @RequestBody Department dto) throws EntityNotFoundException {
		return new ResponseEntity<>(departmentProxyService.update(id, dto), HttpStatus.OK);
	}

	@Operation(summary = "Delete Department", description = "Delete Department for "+REFURB+" system",security = @SecurityRequirement(name = BEARER_AUTH),tags = { DEPARTMENT_TAGS })
	@ApiResponses(value = {
			@ApiResponse(responseCode = STATUS_CODE_OK, description = SUCCESS_MESSAGE, content = @Content(schema = @Schema(implementation = Department.class), mediaType = "application/json")),
			@ApiResponse(responseCode = STATUS_CODE_BAD_REQUEST, description = BAD_REQUESTS_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")),
			@ApiResponse(responseCode = STATUS_AUTHORISED, description = AUTHORISED_USER_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")),
			@ApiResponse(responseCode = STATUS_NOT_FOUND, description = NOT_FOUND_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")) })
	@DeleteMapping(DEPARTMENT_ID)
	public ResponseEntity<Void> delete(@PathVariable(DEPARTMENTS_ID) Integer id) throws EntityNotFoundException {
		departmentProxyService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@Operation(summary = "Retrieve Department", description = "Retrieve Department for "+REFURB+" system",security = @SecurityRequirement(name = BEARER_AUTH), tags = { DEPARTMENT_TAGS })
	@ApiResponses(value = {
			@ApiResponse(responseCode = STATUS_CODE_OK, description = SUCCESS_MESSAGE, content = @Content(schema = @Schema(implementation = Department.class), mediaType = "application/json")),
			@ApiResponse(responseCode = STATUS_CODE_BAD_REQUEST, description = BAD_REQUESTS_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")),
			@ApiResponse(responseCode = STATUS_AUTHORISED, description = AUTHORISED_USER_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")),
			@ApiResponse(responseCode = STATUS_NOT_FOUND, description = NOT_FOUND_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")) })
	@GetMapping
	public ResponseEntity<Page<Department>> getAll(
			@RequestParam(value = "pageNo", defaultValue = DEFAULT_PAGE_NUMBER, required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = DEFAULT_SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = DEFAULT_SORT_DIRECTION, required = false) String sortDir,
			@RequestParam(value = PAGEABLE, defaultValue = "true", required = false) boolean pageableStatus) {
		return new ResponseEntity<>(departmentProxyService.getAll(pageNo, pageSize, sortBy, sortDir,pageableStatus), HttpStatus.OK);
	}

	@Operation(summary = "Get Department", description = "Get Department for "+REFURB+" system",security = @SecurityRequirement(name = BEARER_AUTH), tags = { DEPARTMENT_TAGS} )
	@ApiResponses(value = {
			@ApiResponse(responseCode = STATUS_CODE_OK, description = SUCCESS_MESSAGE, content = @Content(schema = @Schema(implementation = Department.class), mediaType = "application/json")),
			@ApiResponse(responseCode = STATUS_CODE_BAD_REQUEST, description = BAD_REQUESTS_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")),
			@ApiResponse(responseCode = STATUS_AUTHORISED, description = AUTHORISED_USER_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")),
			@ApiResponse(responseCode = STATUS_NOT_FOUND, description = NOT_FOUND_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")) })
	@GetMapping(DEPARTMENT_ID)
	public ResponseEntity<Department> getById(@PathVariable(DEPARTMENTS_ID) Integer id) throws EntityNotFoundException {
		return new ResponseEntity<>(departmentProxyService.getById(id), HttpStatus.OK);
	}


}
