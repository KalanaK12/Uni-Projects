package com.septp0402.orchestratorservice.config;

import org.springframework.web.filter.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        // build a new configuration
        CorsConfiguration configuration = new CorsConfiguration();

        // set the parameters
        configuration.addAllowedOrigin("http://localhost:3000");
        configuration.setAllowCredentials(true);
        configuration.addAllowedHeader("*");
        // TODO could change this in the future for only the methods we need (e.g GET, PUT, DELETE...)
        configuration.addAllowedMethod("*");

        // set a urlBasedCorsConfigurationSource to allow us to map our configuration to all path paters
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        // return a new corsFilter object with the source
        return new CorsFilter(source);

    }
}
