//package com.houstondirectauto.refurb.config;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.stereotype.Component;
//
//import com.fasterxml.jackson.annotation.JsonProperty;
//
//import lombok.Getter;
//import lombok.Setter;
//
//@ConfigurationProperties("s3-properties")
//@Component
//@Getter
//@Setter
//public class S3ConfigurationProperties {
//
//    @JsonProperty(value = "endpoint-URL" )
//    private String endpointUrl;
//    private String region;
//    @JsonProperty("access-key")
//    private String accessKey;
//    @JsonProperty("secret-key")
//    private String secretKey;
//
//
//}