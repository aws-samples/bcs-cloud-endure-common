// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package aws.proserve.bcs.ce.config;

import aws.proserve.bcs.ce.service.Session;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagement;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagementClientBuilder;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan({
        "aws.proserve.bcs.ce.service",
        "aws.proserve.bcs.dr.project",
        "aws.proserve.bcs.dr.secret",
})
@PropertySource("classpath:cloudendure.properties")
public class CloudEndureCommonConfig {

    @Value("${api.url}")
    private String apiUrl;

    private final Session session;

    CloudEndureCommonConfig(Session session) {
        this.session = session;
    }

    @Bean
    AmazonS3 s3() {
        return AmazonS3ClientBuilder.standard().enableForceGlobalBucketAccess().build();
    }

    @Bean
    AWSSecretsManager secretsManager() {
        return AWSSecretsManagerClientBuilder.standard().build();
    }

    @Bean
    AWSSimpleSystemsManagement ssm() {
        return AWSSimpleSystemsManagementClientBuilder.defaultClient();
    }

    /**
     * @apiNote Only the {@code RestTemplate} built by {@code RestTemplateBuilder} can be successfully authenticated by
     * cloud-endure console. Self built {@code RestTemplate} fails.
     */
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplateBuilder().rootUri(apiUrl)
                .interceptors((request, body, execution) -> {
                    if (session.getSecret() != null) {
                        request.getHeaders().set("X-XSRF-TOKEN", session.getSecret());
                    }

                    return execution.execute(request, body);
                }).build();
    }

    /**
     * @apiNote Spring boot provides a primary object mapper which overrides this one.
     */
    @Bean
    ObjectMapper objectMapper() {
        return Jackson2ObjectMapperBuilder
                .json()
                .modules(new JavaTimeModule())
                .serializationInclusion(JsonInclude.Include.NON_NULL) // Don’t include null values
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS) //ISODate
                .build();
    }
}
