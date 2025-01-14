package com.agoh.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Permite todos los endpoints
                        .allowedOrigins("*") // Permite todas las URLs
                        .allowedMethods("*") // Permite todos los métodos HTTP
                        .allowedHeaders("*") // Permite todos los headers
                        .allowCredentials(true); // Permite envío de credenciales
            }
        };
    }
}


