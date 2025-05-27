package com.septp0402.orchestratorservice.config;

import feign.RequestInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    HttpServletRequest request;

    @Autowired
    public FeignConfig(HttpServletRequest request) {
        this.request = request;
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            // forward the authorisation header
            String authorisationHeader = request.getHeader("Authorisation");
            if (authorisationHeader != null) {
                requestTemplate.header("Authorisation", authorisationHeader);
            }

            // ensure content type is json
            requestTemplate.header("Content-Type", "application/json");
        };
    }
}