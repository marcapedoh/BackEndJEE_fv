package com.example.backend.auth;


import com.example.backend.config.CustomUserService;
import com.example.backend.config.JwtService;
import com.example.backend.dto.*;
import com.example.backend.exception.EntityNotFoundException;
import com.example.backend.model.Role;
import com.example.backend.model.User;
import com.example.backend.model.enums.UserType;
import com.example.backend.repository.RoleRepository;
import com.example.backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomUserService customUserService;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new EntityNotFoundException("aucun utilisateur n'est trouvé!"));
        String jwtToken = "";
        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            user.setAuthorities();
            System.out.println(user.getAuthorities());
            jwtToken = jwtService.generateToken(user);
        } else {
            throw new RuntimeException("Bad credentials.");
        }

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Any user already exists with this username");
        }

        Role role;
        if (roleRepository.existsByRoleType(UserType.USER)) {
            role = roleRepository.findByRoleType(UserType.USER).orElseThrow(
                    () -> new EntityNotFoundException("Role not found with type USER")
            );
        } else {
            role = roleRepository.save(
                    Role.builder()
                            .roleName(UserType.USER.name())
                            .roleType(UserType.USER)
                            .build()
            );
        }

        User user = User.builder()
                .role(role)
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .username(request.getUsername())
                .build();
        user = userRepository.save(user);

        String jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public User updateUser(UpdateUserRequest updateUserRequest) {
        User user = getAuthenticatedUser();

        if (!user.getUsername().equals(updateUserRequest.getUsername()) && userRepository.existsByUsername(updateUserRequest.getUsername())) {
            throw new RuntimeException("Any user already exists with username : " + updateUserRequest.getUsername());
        }

        user.setUsername(updateUserRequest.getUsername());
        user.setName(updateUserRequest.getName());
        user = userRepository.save(user);

        return user;
    }

    @Transactional
    public AuthenticationResponse changedPassword(ChangePasswordRequest changedPasswordTemplate) {
        User user = customUserService.getCurrentUser();

        if (!passwordEncoder.matches(changedPasswordTemplate.getCurrentPassword(), user.getPassword())) {
            throw new RuntimeException("Bad credentials. Current password not authorized");
        }

        user.setPassword(passwordEncoder.encode(changedPasswordTemplate.getNewPassword()));
        user = userRepository.save(user);

        String jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public User getAuthenticatedUser() {
        return customUserService.getCurrentUser();
    }


}
