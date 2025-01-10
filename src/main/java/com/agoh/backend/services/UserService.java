//este se creo para el frontend
package com.agoh.backend.services;

import com.agoh.backend.dto.LoginRequest;
import com.agoh.backend.dto.LoginResponse;
import com.agoh.backend.entities.UserEntity;
import com.agoh.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;  // Usa un codificador de contraseñas

    public LoginResponse authenticate(LoginRequest request) {
        UserEntity user = userRepository.findUserEntityByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Compara la contraseña con la almacenada (encriptada)
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        // Crear respuesta, puede incluir un token JWT
        LoginResponse response = new LoginResponse();
        response.setUsername(user.getUsername());
        response.setToken("FAKE_JWT_TOKEN");  // Reemplaza con un JWT real
        return response;
    }
}
