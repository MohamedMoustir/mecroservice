package com.streaming.gateway.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration Swagger / OpenAPI 3 pour la Gateway.
 * L'UI Swagger de la Gateway agrège la documentation de tous les microservices.
 * Accessible via : http://localhost:8080/swagger-ui.html
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI gatewayOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Streaming Platform – API Gateway")
                        .description("Point d'entrée unique pour tous les microservices de la plateforme de streaming. " +
                                "Sélectionnez un service dans la liste déroulante pour consulter sa documentation.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Streaming Platform Team")
                                .email("contact@streaming.com")));
    }
}

