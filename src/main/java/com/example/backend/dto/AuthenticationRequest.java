package com.example.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
