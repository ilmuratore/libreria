package it.epicode.libreria.openapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenApiCustomizer customOpenApiCustomizer() {
        return new OpenApiCustomizer();
    }

    private class OpenApiCustomizer {

    }
}
