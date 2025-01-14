package com.agoh.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.agoh.backend.services.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
            .csrf(csrf -> csrf.disable())
            .cors(Customizer.withDefaults()) // Habilitar CORS desde CorsConfig
            .httpBasic(Customizer.withDefaults())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(http -> {
                // Configurar los endpoints públicos
                http.requestMatchers(
                        "/swagger-ui/**", "/v3/api-docs/**", "/api/auth/login").permitAll();
                
                // Permitir solicitudes OPTIONS para preflight requests
                http.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();

                // Configurar los endpoints privados
                http.requestMatchers(HttpMethod.GET, "/api/v1/promotion/listar").hasAuthority("READ");
                http.requestMatchers(HttpMethod.GET, "/api/v1/promotion/listar/{id}").hasAuthority("READ");
                http.requestMatchers(HttpMethod.POST, "/api/v1/promotion/crear").hasAuthority("CREATE");
                http.requestMatchers(HttpMethod.PUT, "/api/v1/promotion/actualizar/{id}").hasAuthority("UPDATE");
                http.requestMatchers(HttpMethod.DELETE, "/api/v1/promotion/eliminar/{id}").hasAuthority("DELETE");

                // Configurar el resto de endpoints - Requiere autenticación
                http.anyRequest().authenticated();
            })
            .build();
}


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailServiceImpl userDetailService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailService);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
