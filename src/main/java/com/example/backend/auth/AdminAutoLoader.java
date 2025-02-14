package com.example.backend.auth;

import com.example.backend.model.Role;
import com.example.backend.model.User;
import com.example.backend.model.enums.UserType;
import com.example.backend.repository.RoleRepository;
import com.example.backend.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminAutoLoader {

    private final Environment environment;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public AdminAutoLoader(Environment environment, PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository) {
        this.environment = environment;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init() {
        loadAdminUser();
    }

    @Transactional
    public void loadAdminUser() {
        if (roleRepository.existsByRoleType(UserType.ADMIN)) {
            return;
        }

        System.out.println("### AdminAutoLoader:loadAdminUser started ###");

        Role role = roleRepository.save(
                Role.builder()
                        .roleName(UserType.ADMIN.name())
                        .roleType(UserType.ADMIN)
                        .build()
        );

        String username = environment.getProperty("admin.username");
        String password = environment.getProperty("admin.password");

        User user = new User();
        user.setRole(role);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);

        System.out.println("### AdminAutoLoader:loadAdminUser ended ###");
    }

}
