package com.example.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChangePasswordRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String currentPassword;

    @NotBlank
    private String newPassword;

}
