//este se creo para el frontend
package com.agoh.backend.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;  // Para guardar un token JWT, si decides implementarlo
    private String username;
}
