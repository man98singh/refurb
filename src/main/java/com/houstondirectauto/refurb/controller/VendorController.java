package com.houstondirectauto.refurb.controller;

import com.houstondirectauto.refurb.exception.BadRequestException;
import com.houstondirectauto.refurb.exception.EntityNotFoundException;
import com.houstondirectauto.refurb.model.Vendor;
import com.houstondirectauto.refurb.proxy.VendorProxyService;
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
@RequestMapping(VENDOR)
public class VendorController {

	private final VendorProxyService vendorProxyService;

	/**
	 * UserController constructor
	 *
	 * @param VendorController
	 */
	@Autowired
	public VendorController(VendorProxyService vendorProxyService) {
		this.vendorProxyService = vendorProxyService;
	}


	@Operation(summary = "Create Vendor", description = "Create Vendor "+REFURB, security = @SecurityRequirement(name = BEARER_AUTH), tags = { VENDOR_TAGS })
	@ApiResponses(value = {
			@ApiResponse(responseCode = STATUS_CODE_OK, description = SUCCESS_MESSAGE, content = @Content(schema = @Schema(implementation = Vendor.class), mediaType = "application/json")),
			@ApiResponse(responseCode = STATUS_CODE_BAD_REQUEST, description = BAD_REQUESTS_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")),
			@ApiResponse(responseCode = STATUS_AUTHORISED, description = AUTHORISED_USER_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")),
			@ApiResponse(responseCode = STATUS_NOT_FOUND, description = NOT_FOUND_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")) })
	@PostMapping
	public ResponseEntity<Vendor> create(@Valid @RequestBody Vendor dto)
			throws BadRequestException {
		return new ResponseEntity<>(vendorProxyService.create(dto), HttpStatus.CREATED);
	}

	@Operation(summary = "Update Vendor", description = "Update Vendor "+REFURB,security = @SecurityRequirement(name = BEARER_AUTH), tags = { VENDOR_TAGS })
	@ApiResponses(value = {
			@ApiResponse(responseCode = STATUS_CODE_OK, description = SUCCESS_MESSAGE, content = @Content(schema = @Schema(implementation = Vendor.class), mediaType = "application/json")),
			@ApiResponse(responseCode = STATUS_CODE_BAD_REQUEST, description = BAD_REQUESTS_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")),
			@ApiResponse(responseCode = STATUS_AUTHORISED, description = AUTHORISED_USER_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")),
			@ApiResponse(responseCode = STATUS_NOT_FOUND, description = NOT_FOUND_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")) })
	@PutMapping(VENDOR_ID)
	public ResponseEntity<Vendor> update(@PathVariable(VENDORS_ID) Integer id,
													@Valid @RequestBody Vendor dto) throws EntityNotFoundException {
		return new ResponseEntity<>(vendorProxyService.update(id, dto), HttpStatus.OK);
	}

	@Operation(summary = "Delete Vendor", description = "Delete vendor for "+REFURB+" system",security = @SecurityRequirement(name = BEARER_AUTH),tags = { VENDOR_TAGS })
	@ApiResponses(value = {
			@ApiResponse(responseCode = STATUS_CODE_OK, description = SUCCESS_MESSAGE, content = @Content(schema = @Schema(implementation = Vendor.class), mediaType = "application/json")),
			@ApiResponse(responseCode = STATUS_CODE_BAD_REQUEST, description = BAD_REQUESTS_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")),
			@ApiResponse(responseCode = STATUS_AUTHORISED, description = AUTHORISED_USER_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")),
			@ApiResponse(responseCode = STATUS_NOT_FOUND, description = NOT_FOUND_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")) })
	@DeleteMapping(VENDOR_ID)
	public ResponseEntity<Void> delete(@PathVariable(VENDORS_ID) Integer id) throws EntityNotFoundException {
		vendorProxyService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@Operation(summary = "Retrieve Vendor", description = "Retrieve Vendor for "+REFURB+" system",security = @SecurityRequirement(name = BEARER_AUTH), tags = { VENDOR_TAGS })
	@ApiResponses(value = {
			@ApiResponse(responseCode = STATUS_CODE_OK, description = SUCCESS_MESSAGE, content = @Content(schema = @Schema(implementation = Vendor.class), mediaType = "application/json")),
			@ApiResponse(responseCode = STATUS_CODE_BAD_REQUEST, description = BAD_REQUESTS_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")),
			@ApiResponse(responseCode = STATUS_AUTHORISED, description = AUTHORISED_USER_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")),
			@ApiResponse(responseCode = STATUS_NOT_FOUND, description = NOT_FOUND_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")) })
	@GetMapping
	public ResponseEntity<Page<Vendor>> getAll(
			@RequestParam(value = "pageNo", defaultValue = DEFAULT_PAGE_NUMBER, required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = DEFAULT_SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = DEFAULT_SORT_DIRECTION, required = false) String sortDir,
			@RequestParam(value = PAGEABLE, defaultValue = "true", required = false) boolean pageableStatus) {
		return new ResponseEntity<>(vendorProxyService.getAll(pageNo, pageSize, sortBy, sortDir,pageableStatus), HttpStatus.OK);
	}

	@Operation(summary = "Get Vendor", description = "Get Vendor for "+REFURB+" system",security = @SecurityRequirement(name = BEARER_AUTH), tags = { VENDOR_TAGS} )
	@ApiResponses(value = {
			@ApiResponse(responseCode = STATUS_CODE_OK, description = SUCCESS_MESSAGE, content = @Content(schema = @Schema(implementation = Vendor.class), mediaType = "application/json")),
			@ApiResponse(responseCode = STATUS_CODE_BAD_REQUEST, description = BAD_REQUESTS_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")),
			@ApiResponse(responseCode = STATUS_AUTHORISED, description = AUTHORISED_USER_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")),
			@ApiResponse(responseCode = STATUS_NOT_FOUND, description = NOT_FOUND_MESSAGE, content = @Content(schema = @Schema(implementation = Error.class), mediaType = "application/json")) })
	@GetMapping(VENDOR_ID)
	public ResponseEntity<Vendor> getById(@PathVariable(VENDORS_ID) Integer id) throws EntityNotFoundException {
		return new ResponseEntity<>(vendorProxyService.getById(id), HttpStatus.OK);
	}


}
