package com.streaming.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration config = new CorsConfiguration();

        // setAllowedOriginPatterns est compatible avec setAllowCredentials(true)
        config.setAllowedOriginPatterns(List.of("*"));

        // Autoriser toutes les méthodes HTTP (GET, POST, PUT, DELETE, OPTIONS, PATCH...)
        config.setAllowedMethods(List.of("*"));

        // Autoriser tous les headers
        config.setAllowedHeaders(List.of("*"));

        // Exposer les headers de réponse au client
        config.setExposedHeaders(List.of("Authorization", "Content-Type"));

        // Autoriser les credentials
        config.setAllowCredentials(true);

        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // Appliquer la config CORS à TOUS les endpoints
        source.registerCorsConfiguration("/**", config);

        return new CorsWebFilter(source);
    }
}