//package com.houstondirectauto.refurb.util;
//
//import java.util.Map;
//import java.util.Set;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.ResourceAccessException;
//import org.springframework.web.client.RestClientException;
//import org.springframework.web.client.RestTemplate;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.http.HttpEntity;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Service
//@Slf4j
//public class RestClientUtility {
//
//	@Autowired
//	private RestTemplate restTemplate;
//
//
//	/*
//	 * If headersMap is not null then HttpMethod is required
//	 */
//	/**
//	 * @param <T>
//	 * @param url
//	 * @param headersMap
//	 * @param clazz
//	 * @param method
//	 * @param payload
//	 * @return
//	 */
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	public <T> T apiManagerForJson(String url, Map<String, String> headersMap, Class<T> clazz, HttpMethod method,
//			Object payload) {
//
//
////		RefurbToken userStr = restTemplate.getForObject(Constants.REFURB_TOKEN, RefurbToken.class);
////		IdmsToken userStr = restTemplate.getForObject(Constants.IDMS_TOKEN, IdmsToken.class);
////		System.out.println("userStr"+userStr);
//
//		log.info("Url {}",url );
//
//		T result = null;
//		if (headersMap == null) {
//			try {
//
//				if (payload == null)
//					result = restTemplate.getForObject(url, clazz);
//				else
//					result = restTemplate.postForObject(url, payload, clazz);
//			} catch (ResourceAccessException e) {
//				e.printStackTrace();
//				log.error("ResourceAccessException " + e.getMessage());
////				throw new ServiceUnavailableException(ApiConstant.URL_ERROR);
//			} catch (RestClientException e) {
//				e.printStackTrace();
//				log.error("RestClientException " + e.getMessage());
////				throw new ServiceUnavailableException(ApiConstant.ADDRESS_NOT_FOUND);
//			} catch (Exception e) {
//				e.printStackTrace();
//				log.error("Exception " + e.getMessage());
////				throw new ServiceUnavailableException(e.getMessage());
//			}
//		} else {
//			HttpHeaders headers = new HttpHeaders();
//			Set<String> keySet = headersMap.keySet();
//			for (String s : keySet) {
//				headers.set(s, headersMap.get(s));
//			}
//			HttpEntity request = new HttpEntity(headers);
//			if (payload != null) {
//				request = new HttpEntity<Object>(payload, headers);
//			}
//
//			ResponseEntity<T> response = null;
//			try {
//				ObjectMapper mapper = new ObjectMapper();
////				  log.info("Json Request \n" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(request));
//				response = restTemplate.exchange(url, method, request, clazz);
//			} catch (ResourceAccessException e) {
//				e.printStackTrace();
//				log.error("ResourceAccessExceptions " + e.getMessage());
////				throw new ServiceUnavailableException(ApiConstant.URL_ERROR);
//			} catch (RestClientException e) {
//				e.printStackTrace();
////				e.printStackTrace();
//				log.error("RestClientExceptions " + e.getMessage());
////				throw new ServiceUnavailableException(ApiConstant.ADDRESS_NOT_FOUND);
//			} catch (Exception e) {
//				e.printStackTrace();
//				log.error("Exceptions " + e.getMessage());
////				throw new ServiceUnavailableException(ApiConstant.ADDRESS_NOT_FOUND);
//			}
//			result = response.getBody();
//		}
////		log.info("result"+result);
//		return result;
////		return null;
//	}
//
//}
