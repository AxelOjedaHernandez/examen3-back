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
                        .allowedOrigins("https://examen3-front-2ycd2vt8t-axels-projects-8148f110.vercel.app") // Permite solo el dominio de la app de front
                        .allowedMethods("*") // Permite todos los métodos HTTP
                        .allowedHeaders("*") // Permite todos los headers
                        .allowCredentials(true); // permite enviar credenciales
            }
        };
    }
}

