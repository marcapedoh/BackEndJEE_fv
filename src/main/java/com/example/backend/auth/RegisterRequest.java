package com.example.backend.auth;

import com.example.backend.model.UserType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserType userType;
}
