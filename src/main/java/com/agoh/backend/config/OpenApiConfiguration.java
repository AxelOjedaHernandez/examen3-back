package com.agoh.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
@OpenAPIDefinition(
    info = @Info(title = "API de promociones", 
    description = "Esta API proporciona acceso a los servicio de promociones", 
    version = "1.0.0", 
    contact = @Contact(name = "Axel Giovanni Ojeda Hernandez", 
    email = "axelojedahernandez1800@alumno.ipn.mx", 
    url = "http://localhost:8080/contacto"), 
    license = @License(), 
    termsOfService = "Solo se permite el uso de la API para fines educativos"), 
    servers = { 
        @Server(description = "Servidor de pruebas", 
        url = "http://localhost:8080")
    }, 
    tags = {
        @Tag(name = "Promotion", 
        description = "Endpoint para los recursos de Promociones")
    }
)

public class OpenApiConfiguration {
    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "basicAuth";
        return new OpenAPI()
        .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
        .components(new Components()
        .addSecuritySchemes(securitySchemeName, new SecurityScheme().name(securitySchemeName).type(SecurityScheme.Type.HTTP).scheme("basic")));
    }
}
