//package com.houstondirectauto.refurb.config;
//
//import java.net.URI;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//
//import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
//import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
//import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
//import software.amazon.awssdk.regions.Region;
//import software.amazon.awssdk.services.s3.S3Client;
//
//@Configuration
//class S3Config {
//
//    private S3ConfigurationProperties s3ConfigurationProperties;
//
//    @Autowired
//    public S3Config(S3ConfigurationProperties s3ConfigurationProperties) {
//        this.s3ConfigurationProperties = s3ConfigurationProperties;
//    }
//
//    /**
//     *  Configures S3 client with credentials & region.
//     * @return s3-client
//     */
//    @Bean
//    public S3Client s3Client() {
//        AwsBasicCredentials credentials = AwsBasicCredentials.create(s3ConfigurationProperties.getAccessKey(),
//                s3ConfigurationProperties.getSecretKey());
//        AwsCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(credentials);
//        return S3Client.builder()
//                .credentialsProvider(credentialsProvider)
//                .endpointOverride(URI.create(s3ConfigurationProperties.getEndpointUrl()))
//                .region(Region.of(s3ConfigurationProperties.getRegion()))
//                .build();
//    }
//}