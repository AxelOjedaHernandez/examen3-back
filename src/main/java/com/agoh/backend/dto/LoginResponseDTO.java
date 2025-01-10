package com.agoh.backend.dto;

public class LoginResponseDTO {
    private Long id;
    private String username;

    public LoginResponseDTO(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    // Getter para 'id'
    public Long getId() {
        return id;
    }

    // Setter para 'id'
    public void setId(Long id) {
        this.id = id;
    }

    // Getter para 'username'
    public String getUsername() {
        return username;
    }

    // Setter para 'username'
    public void setUsername(String username) {
        this.username = username;
    }
}
