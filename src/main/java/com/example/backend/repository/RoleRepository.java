package com.example.backend.repository;

import com.example.backend.model.Role;
import com.example.backend.model.enums.UserType;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    boolean existsByRoleName(@NotBlank(message = "roleName is required") String roleName);

    boolean existsByRoleType(UserType roleType);

    Optional<Role> findByRoleType(UserType roleType);
}
