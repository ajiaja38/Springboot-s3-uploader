package com.uploader.spring.config;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.uploader.spring.utils.constant.ApiBeanConstant;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {

    @Value("${biznet.s3.access-key}")
    private String accessKey;

    @Value("${biznet.s3.secret-key}")
    private String secretKey;

    @Value("${biznet.s3.endpoint}")
    private String endpoint;

    @Value("${biznet.s3.region}")
    private String region;

    @Bean(name = ApiBeanConstant.BIZNETS3)
    S3Client s3Client() {
        return S3Client.builder()
                .endpointOverride(URI.create("https://" + endpoint))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(accessKey, secretKey)))
                .region(Region.of(region))
                .forcePathStyle(true)
                .build();
    }

}
