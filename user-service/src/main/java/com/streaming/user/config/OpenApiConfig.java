package com.streaming.user.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration Swagger / OpenAPI 3 pour le User Service.
 * Accessible via : http://localhost:8082/swagger-ui.html
 * ou via la Gateway : http://localhost:8080/swagger-ui.html (URL sélectionnée : user-service)
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI userServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("User Service API")
                        .description("API REST pour la gestion des utilisateurs, watchlist et historique de visionnage.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Streaming Platform Team")
                                .email("contact@streaming.com")));
    }
}

