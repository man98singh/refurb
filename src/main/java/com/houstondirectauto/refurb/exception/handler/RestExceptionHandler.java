package com.houstondirectauto.refurb.exception.handler;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

import jakarta.persistence.NonUniqueResultException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.houstondirectauto.refurb.exception.ApiError;
import com.houstondirectauto.refurb.exception.BadRequestException;
import com.houstondirectauto.refurb.exception.EntityNotFoundException;
import com.houstondirectauto.refurb.exception.S3KeyDoesNotExistException;
import com.houstondirectauto.refurb.exception.ServiceUnavailableException;
import com.houstondirectauto.refurb.exception.SqlException;
import com.houstondirectauto.refurb.exception.UnauthorizedException;

import lombok.extern.slf4j.Slf4j;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;

/**
 * RestExceptionHandler Class For Handling Exceptions
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	private final MessageSource messageSource;


	@Autowired
	public RestExceptionHandler(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	/**
	 * Handle MissingServletRequestParameterException. Triggered when a 'required'
	 * request parameter is missing.
	 *
	 * @param ex      MissingServletRequestParameterException
	 * @param headers HttpHeaders
	 * @param status  HttpStatus
	 * @param request WebRequest
	 * @return the ApiError object
	 */
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
																		  HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		String error = ex.getParameterName().toUpperCase() + "is mandatory";
		log.error(error);
		return buildResponseEntity(new ApiError(BAD_REQUEST, error, ex));
	}

	/**
	 * Handle HttpMediaTypeNotSupportedException. This one triggers when JSON is
	 * invalid as well.
	 *
	 * @param ex      HttpMediaTypeNotSupportedException
	 * @param headers HttpHeaders
	 * @param status  HttpStatus
	 * @param request WebRequest
	 * @return the ApiError object
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		log.error("UNSUPPORTED_MEDIA_TYPE");

		ApiError apiError = new ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE);

		return buildResponseEntity(apiError);

	}

	/**
	 * Handle MethodArgumentNotValidException. Triggered when an object fails @Valid
	 * validation.
	 *
	 * @param ex      the MethodArgumentNotValidException that is thrown when @Valid
	 *                validation fails
	 * @param headers HttpHeaders
	 * @param status  HttpStatus
	 * @param request WebRequest
	 * @return the ApiError object
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		Locale locale = request.getLocale();
		BindingResult result = ex.getBindingResult();

		List<FieldError> errorMessage = result.getFieldErrors().stream()
				.map(fieldError -> {
					String message = messageSource.getMessage(fieldError, locale);
					return new FieldError(fieldError.getObjectName(),
							fieldError.getField(), message);
				})
				.collect(Collectors.toList());

		ApiError apiError = new ApiError(BAD_REQUEST);
		apiError.setMessage("Validation error");
		apiError.addValidationErrors(errorMessage);

		return buildResponseEntity(apiError);
	}

	/**
	 * Handle HttpMessageNotWritableException.
	 *
	 * @param ex      HttpMessageNotWritableException
	 * @param headers HttpHeaders
	 * @param status  HttpStatus
	 * @param request WebRequest
	 * @return the ApiError object
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		String error = "INTERNAL_SERVER_ERROR";

		return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, error, ex));
	}

	/**
	 * Handle HttpMessageNotReadableException. Happens when request JSON is
	 * malformed.
	 *
	 * @param ex      HttpMessageNotReadableException
	 * @param headers HttpHeaders
	 * @param status  HttpStatus
	 * @param request WebRequest
	 * @return the ApiError object
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		String error = ex.getMessage();
		return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
	}

	/**
	 * Handles javax.validation.ConstraintViolationException. Thrown when @Validated
	 * fails.
	 *
	 * @param ex the ConstraintViolationException
	 * @return the ApiError object
	 */
	@ExceptionHandler(jakarta.validation.ConstraintViolationException.class)
	protected ResponseEntity<Object> handleConstraintViolation(jakarta.validation.ConstraintViolationException ex) {

		ApiError apiError = new ApiError(BAD_REQUEST);
		apiError.setMessage("Validation error");
		apiError.addValidationErrors(ex.getConstraintViolations());

		return buildResponseEntity(apiError);
	}

	/**
	 * Handle DataIntegrityViolationException, inspects the cause for different DB
	 * causes.
	 *
	 * @param ex      the DataIntegrityViolationException
	 * @param request WebRequest
	 * @return the ApiError object
	 */
	@ExceptionHandler(DataIntegrityViolationException.class)
	protected ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex,
			WebRequest request) {

		if (ex.getCause() instanceof ConstraintViolationException) {
			return buildResponseEntity(new ApiError(HttpStatus.CONFLICT, "Some thing wrong with database", ex.getCause()));
		}else if(ex.getCause() instanceof DataException) {
			return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, "Entered value to large ", ex.getCause()));
		}

		return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex));
	}

	/**
	 * Handle Exception, handle generic Exception.class
	 *
	 * @param ex      the Exception
	 * @param request WebRequest
	 * @return the ApiError object
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
			WebRequest request) {



		ApiError apiError = new ApiError(BAD_REQUEST);
		apiError.setMessage("Bad Request try again!");
		apiError.setDebugMessage(ex.getMessage());

		return buildResponseEntity(apiError);
	}

	/**
	 * Handles EntityNotFoundException. Created to encapsulate errors with more
	 * detail than javax.persistence.EntityNotFoundException.
	 *
	 * @param ex the EntityNotFoundException Object
	 * @return the ApiError Object
	 */
	@ExceptionHandler(EntityNotFoundException.class)
	protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {

		ApiError apiError = new ApiError(NOT_FOUND);
		apiError.setMessage(ex.getMessage());

		return buildResponseEntity(apiError);
	}

	/**
	 * Handles BadRequestException. Created to encapsulate errors with more detail
	 * than javax.persistence.BadRequestException.
	 *
	 * @param ex the BadRequestException Object
	 * @return the ApiError Object
	 */
	@ExceptionHandler(BadRequestException.class)
	protected ResponseEntity<Object> handleBadRequestException(BadRequestException ex) {

		ApiError apiError = new ApiError(BAD_REQUEST);
		apiError.setMessage(ex.getMessage());

		return buildResponseEntity(apiError);
	}

	/**
	 * Handles ServiceUnavailableException. Created to encapsulate errors with more
	 * detail than javax.persistence.ServiceUnavailableException.
	 *
	 * @param ex the ServiceUnavailableException Object
	 * @return the ApiError Object
	 */
	@ExceptionHandler(ServiceUnavailableException.class)
	protected ResponseEntity<Object> handleServiceUnavailableExceptionRequestException(ServiceUnavailableException ex,
			WebRequest webRequest) {

		ApiError apiError = new ApiError(HttpStatus.SERVICE_UNAVAILABLE);
		return buildResponseEntity(apiError);
	}

	/**
	 * Handles UnauthorizedException. Created to encapsulate errors with more detail
	 * than javax.persistence.UnauthorizedException.
	 *
	 * @param ex the UnauthorizedException Object
	 * @return the ApiError Object
	 */
	@ExceptionHandler(UnauthorizedException.class)
	protected ResponseEntity<Object> handleUnauthorizedException(UnauthorizedException ex) {

		ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED);
		apiError.setMessage(ex.getMessage());

		return buildResponseEntity(apiError);
	}

	/**
	 * Handles IllegalArgumentException. Created to encapsulate errors with more
	 * detail than javax.persistence.UnauthorizedException.
	 *
	 * @param ex the IllegalArgumentException Object
	 * @return the ApiError Object
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	protected ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {

		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
		apiError.setMessage(ex.getMessage());

		return buildResponseEntity(apiError);
	}

	/**
	 * Handles IllegalArgumentException. Created to encapsulate errors with more
	 * detail than javax.persistence.UnauthorizedException.
	 *
	 * @param ex the IllegalArgumentException Object
	 * @return the ApiError Object
	 */
//	@ExceptionHandler(InvalidRelationServiceException.class)
//	protected ResponseEntity<Object> handleExternalServiceError(ExternalServiceException ex) {
//
//		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
//		apiError.setMessage(ex.getMessage());
//
//		return buildResponseEntity(apiError);
//	}

	/**
	 * Handler AuthenticationException. Created to encapsulate errors with more
	 * detail than org.springframework.security.core.AuthenticationException.
	 *
	 * @param ex AuthenticationException Object
	 * @return the ApiError Object
	 */
//	@ExceptionHandler(AuthenticationException.class)
//	public ResponseEntity<Object> handleAuthenticationException(AuthenticationException ex) {
//
//		ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED);
//		apiError.setMessage(ex.getMessage());
//
//		return buildResponseEntity(apiError);
//	}

	/**
	 * BuildResponseEntity
	 *
	 * @param apiError ApiError Object
	 * @return ResponseEntity ResponseEntity Object
	 */
	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

	/**
	 * Handler SqlException.
	 *
	 * @param ex AuthenticationException Object
	 * @return the ApiError Object
	 */
	@ExceptionHandler(SqlException.class)
	public ResponseEntity<Object> handleSqlException(SqlException ex) {

		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
		apiError.setMessage(ex.getMessage());

		return buildResponseEntity(apiError);
	}

	/**
	 * Handler SqlException.
	 *
	 * @param ex AuthenticationException Object
	 * @return the ApiError Object
	 */
	@ExceptionHandler(NonUniqueResultException.class)
	public ResponseEntity<Object> handleNonUniqueResultException(NonUniqueResultException ex) {

		ApiError apiError = new ApiError(HttpStatus.CONFLICT);
		return buildResponseEntity(apiError);

	}

	/**
	 * Handles NoSuchElementException. Created to encapsulate errors with more
	 * detail than javax.persistence.NoSuchElementException.
	 *
	 * @param ex the NoSuchElementException Object
	 * @return the ApiError Object
	 */
	@ExceptionHandler(NoSuchElementException.class)
	protected ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException ex) {

		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
		log.error("Exception : " + ExceptionUtils.getStackTrace(ex));
		apiError.setMessage("No element found in the system");
		return buildResponseEntity(apiError);

	}

	public static String getSimpleName(MethodArgumentTypeMismatchException ex) {
		Class<?> genericClass = ex.getRequiredType();
		if (Objects.nonNull(genericClass)) {
			return genericClass.getSimpleName();
		}
		return "Constants.EMPTY_STRING";
	}

	/**
	 * Handles SQLGrammarException. Created to encapsulate errors with more
	 * detail than javax.persistence.ServiceUnavailableException.
	 *
	 * @param ex the SQLGrammarException Object
	 * @return the ApiError Object
	 */
	@ExceptionHandler(SQLGrammarException.class)
	protected ResponseEntity<Object> handleSQLGrammarException(SQLGrammarException ex) {
		ApiError apiError = new ApiError(SERVICE_UNAVAILABLE);
		log.error("SQLGrammarException "+ ex.getMessage());
		apiError.setMessage("Some thing wrong in the database");
		return buildResponseEntity(apiError);
	}

	/**
	 * Handler S3KeyDoesNotExistException.
	 *
	 * @param ex S3KeyDoesNotExistException Object
	 * @return the ApiError Object
	 */
	@ExceptionHandler(S3KeyDoesNotExistException.class)
	public ResponseEntity<Object> handleS3KeyDoesNotExistException(S3KeyDoesNotExistException ex, WebRequest webRequest) {

		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);

		return buildResponseEntity(apiError);

	}


	/**
	 * Handler ConnectException
	 *
	 * @param ex {@link ConnectException}
	 * @param webRequest {@link WebRequest}
	 * @return the ApiError Object
	 */
//	@ExceptionHandler(ConnectException.class)
//	public ResponseEntity<Object> handleConnectException(ConnectException ex,
//			WebRequest webRequest) {
//
//		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
//		errorAuditLogUtil.getApiErrorLogMessage(webRequest, CONNECTION_FAILURE, ex, apiError);
//		return buildResponseEntity(apiError);
//	}

	/**
	 * Handler ClassNotFoundException
	 *
	 * @param ex {@link ClassNotFoundException}
	 * @param webRequest {@link WebRequest}
	 * @return the ApiError Object
	 */
//	@ExceptionHandler(ClassNotFoundException.class)
//	public ResponseEntity<Object> handleClassNotFoundException(ClassNotFoundException ex,
//			WebRequest webRequest) {
//
//		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
//		errorAuditLogUtil.getApiErrorLogMessage(webRequest, CLASS_NOT_FOUND_ERR_MSG, ex, apiError);
//		return buildResponseEntity(apiError);
//	}

	/**
	 * Handler UrlNotFoundException
	 *
	 * @param ex {@link NoHandlerFoundException}
	 * @param headers {@link HttpHeaders}
	 * @param status {@link HttpStatus}
	 * @param webRequest {@link WebRequest}
	 * @return the ApiError Object
	 */
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
																   HttpStatusCode status, WebRequest request) {
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
		return buildResponseEntity(apiError);
	}

	/**
	 * Handler Exception
	 *
	 * @param ex {@link Exception}
	 * @param webRequest {@link WebRequest}
	 * @return the ApiError Object
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handle(Exception ex, WebRequest webRequest) {

		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
		return buildResponseEntity(apiError);
	}

}

