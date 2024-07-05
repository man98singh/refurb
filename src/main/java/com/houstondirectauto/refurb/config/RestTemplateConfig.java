//package com.houstondirectauto.refurb.config;
//
//import java.util.Collections;
//
//import org.springframework.boot.web.client.RestTemplateBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//import org.springframework.web.client.RestTemplate;
//
//@Configuration
//public class RestTemplateConfig {
//	@Bean
//	public RestTemplate restTemplate(RestTemplateBuilder builder)
//	{
//		return builder.build();
//	}
//
//	@Bean
//	public RestTemplate restTemplate() {
//	    RestTemplate restTemplate = new RestTemplate();
//	    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//	    converter.setSupportedMediaTypes(Collections.singletonList(MediaType.TEXT_HTML));
//	    restTemplate.getMessageConverters().add(converter);
//	    return restTemplate;
//	}
//}
