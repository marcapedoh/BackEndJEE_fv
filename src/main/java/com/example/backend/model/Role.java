package com.example.backend.model;

import com.example.backend.model.enums.UserType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "roleName is required")
    @Column(unique = true)
    private String roleName;

    @NotNull(message = "roleType is required")
    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private UserType roleType;

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}