package com.streaming.video.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration Swagger / OpenAPI 3 pour le Video Service.
 * Accessible via : http://localhost:8081/swagger-ui.html
 * ou via la Gateway : http://localhost:8080/swagger-ui.html (URL sélectionnée : video-service)
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI videoServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Video Service API")
                        .description("API REST pour la gestion du catalogue vidéo (Films, Séries, Trailers YouTube).")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Streaming Platform Team")
                                .email("contact@streaming.com")));
    }
}

