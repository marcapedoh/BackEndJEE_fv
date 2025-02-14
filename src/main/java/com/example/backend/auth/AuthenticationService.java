package com.example.backend.auth;


import com.example.backend.config.JwtService;
import com.example.backend.dto.AuthenticationRequest;
import com.example.backend.dto.AuthenticationResponse;
import com.example.backend.dto.ChangePasswordRequest;
import com.example.backend.dto.RegisterRequest;
import com.example.backend.exception.EntityNotFoundException;
import com.example.backend.model.Role;
import com.example.backend.model.User;
import com.example.backend.model.enums.UserType;
import com.example.backend.repository.RoleRepository;
import com.example.backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new EntityNotFoundException("aucun utilisateur n'est trouvé!"));
        String jwtToken = "";
        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
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
                .password(passwordEncoder.encode(request.getPassword()))
                .username(request.getUsername())
                .build();
        user = userRepository.save(user);

        String jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Transactional
    public AuthenticationResponse changedPassword(ChangePasswordRequest changedPasswordTemplate) {
        User user = userRepository.findByUsername(changedPasswordTemplate.getUsername()).orElseThrow(() -> new EntityNotFoundException("aucun utilisateur n'est trouvé!"));

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

}
