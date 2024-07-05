//package com.houstondirectauto.refurb.logs;
//
//import java.lang.annotation.Annotation;
//import java.net.SocketException;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.Objects;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.context.request.RequestAttributes;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import com.houstondirectauto.transport.entity.UserEntity;
//
//@Aspect
//@Component
//public class TransportApplicationLogging {
//	 private static final Logger logger = LogManager.getLogger(TransportApplicationLogging.class);
//
//
//	/**
//	 *
//	 * Printing logs for xchekvetApplication
//	 *
//	 * @param joinPoint
//	 * @param result
//	 * @return JoinPoint object
//	 * @throws SocketException
//	 * @throws JsonProcessingException
//	 */
//
//	@AfterReturning(pointcut = "within(@org.springframework.web.bind.annotation.RestController *)", returning = "result")
//	public Object transportLogger(JoinPoint joinPoint, Object result) {
//		Object[] arguments = joinPoint.getArgs();
//
//		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
//		HttpServletRequest httpServletRequest = null;
//		HttpServletResponse httpServletResponse = null;
//
//		String address = "";
//		String ipAddress = "";
//		String token = "";
//		UserEntity entity = null;
//
//		try {
//			ObjectMapper mapper = new ObjectMapper();
//			httpServletRequest = servletRequestAttributes.getRequest();
//			httpServletResponse = servletRequestAttributes.getResponse();
//			String argument = mapper.writeValueAsString(Arrays.toString(arguments));
//			String[] agrs = argument.split(", ");
//			for (String arg : agrs) {
//				if (arg.contains("Bearer")) {
////					token = arg.replace(AppProperty.getTokenHeaderPrefix(), "");
//					token = token.replace("\"[", "").trim();
////					entity = IdentityManagementUtils.getUser(token);
//				}
//				if (arg.contains("=]")) {
//					address = arg.replace("]\"", "");
////					ipAddress = DecryptionUtil.decryption(address, AppProperty.getAesSecrets());
//				}
//			}
//			String response = mapper.writeValueAsString(result);
//
//			if (Objects.nonNull(entity) && Objects.nonNull(httpServletRequest) && Objects.nonNull(httpServletResponse))
//				logger.info("{} {} [{}] '{}{} {}' {} {}", ipAddress, entity.getEmail(), new Date(),
//						httpServletRequest.getMethod(), httpServletRequest.getRequestURI(),
//						httpServletRequest.getProtocol(), httpServletResponse.getStatus(),
//						httpServletResponse.getBufferSize());
//
//			else if (Objects.nonNull(httpServletRequest) && Objects.nonNull(httpServletResponse))
//				logger.info("Payload info [{}] '{}{} {}' {} {}", new Date(), httpServletRequest.getMethod(),
//						httpServletRequest.getRequestURI(), httpServletRequest.getProtocol(),
//						httpServletResponse.getStatus(), httpServletResponse.getBufferSize());
//
//			return joinPoint;
//		} catch (Exception exception) {
//			logger.error(">>>>>>>error");
//			return transportErrorLog(joinPoint, exception);
////			return joinPoint;
//		}
//
//	}
//
//	/**
//	 * Printing error logs
//	 *
//	 * @param joinPoint
//	 * @param exception
//	 * @return
//	 * @throws SocketException
//	 * @throws JsonProcessingException
//	 */
//	@AfterThrowing(pointcut = "execution(* com.houstondirectauto.transport.service.*.*(..)) || executeController() ", throwing = "exception")
//	public Object transportErrorLog(JoinPoint joinPoint, Throwable exception) {
//		logger.info("inside transportErrorLog");
//		String methodName = joinPoint.getSignature().getName();
//		String className = joinPoint.getTarget().getClass().getName();
//		logger.info("inside transportErrorLog get body");
//
//		getRequestBody(joinPoint);
//
//		logger.error("Exception occurs in: {}: {}().with cause: {} {}", className, methodName, joinPoint,
//				exception.getCause() != null ? exception.getCause() : exception.getMessage());
//		return joinPoint;
//	}
//
//	/**
//	 * Add log in post
//	 */
//	@Pointcut("execution(* com.houstondirectauto.transport.controller..*(.., @org.springframework.web.bind.annotation.RequestBody (*), ..))")
//	public void executeController() {
//		/**
//		 * Printing logs post methods
//		 */
//	}
//
//	/**
//	 * Get post payload
//	 *
//	 * @param thisJoinPoint
//	 */
//	public void getRequestBody(JoinPoint thisJoinPoint) {
//		MethodSignature methodSignature = (MethodSignature) thisJoinPoint.getSignature();
//		logger.info("inside getRequestBody");
//		if (Objects.nonNull(methodSignature)) {
//			logger.info("inside getRequestBody non null");
//			Annotation[][] annotationMatrix = methodSignature.getMethod().getParameterAnnotations();
//			int index = -1;
//			if (Objects.nonNull(annotationMatrix)) {
//				for (Annotation[] annotations : annotationMatrix) {
//					index++;
//					for (Annotation annotation : annotations) {
//						if (!(annotation instanceof RequestBody))
//							continue;
//						Object requestBody = thisJoinPoint.getArgs()[index];
//						logger.info("Requested payload {} :", requestBody);
//					}
//				}
//			}
//		}
//	}
//
//	/**
//	 * @param joinPoint
//	 */
//	@Before("execution(* com.houstondirectauto.transport.proxy..*(..)) || execution(* com.houstondirectauto.transport.service..*(..)) ")
//	public void applicationFlowLog(JoinPoint joinPoint) {
//		logger.debug("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
//				joinPoint.getSignature().getName(), joinPoint.getArgs());
//	}
//
//	/**
//	 * When we need to log request payload
//	 * @param joinPoint
//
//	@Before("within(com.houstondirectauto.transport.controller.*)")
//	public void endpointBefore(JoinPoint p) {
//
//		logger.info(p.getTarget().getClass().getSimpleName() + " " + p.getSignature().getName() + " START");
//		Object[] signatureArgs = p.getArgs();
//
//		ObjectMapper mapper = new ObjectMapper();
//		mapper.enable(SerializationFeature.INDENT_OUTPUT);
//		try {
//
//			if (Objects.nonNull(signatureArgs)) {
//
//				for (int i = 0; i < signatureArgs.length; i++) {
//					if (Objects.nonNull(signatureArgs[i])) {
//
//						if (!mapper.writeValueAsString(signatureArgs[i]).contains("Bearer")) {
//							 logger.info("\nRequest object: \n" + mapper.writeValueAsString(signatureArgs[i]));
//						}
//					}
//				}
//			}
//		} catch (JsonProcessingException e) {
//		}
//	}
//	*/
//
//}
