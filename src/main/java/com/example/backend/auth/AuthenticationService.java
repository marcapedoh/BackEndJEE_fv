package com.example.backend.auth;


import com.example.backend.config.JwtService;
import com.example.backend.exception.EntityNotFoundException;
import com.example.backend.model.Role;
import com.example.backend.model.User;
import com.example.backend.model.UserType;
import com.example.backend.repository.RoleRepository;
import com.example.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthenticationService {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        UserDetails user=userRepository.findByUsername(request.getUsername()).orElseThrow(()-> new EntityNotFoundException("aucun utilisateur n'est trouvé!"));
        String jwtToken="";
        if(passwordEncoder.matches(request.getPassword(),user.getPassword())){
            jwtToken=jwtService.generateToken(user);
        }
        UserDetails user1=userRepository.findByUsername(request.getUsername()).orElseThrow(()-> new EntityNotFoundException("aucun utilisateur n'est trouvé!"));
        /*if(!StringUtils.hasLength(user1.getUsername())){
            log.warn("le username de ce utilisateur est nul");
        }*/

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
    public AuthenticationResponse register(RegisterRequest request) {
        Role role=null;
        if(request.getUserType()== UserType.USER){
            role= roleRepository.save(Role.builder()
                            .roleName("USER")
                    .build());
        }
        var user= User.builder()
                .role(role)
                .password(passwordEncoder.encode(request.getPassword()))
                .username(request.getUsername())

                .build();
        userRepository.save(user);
        var jwtToken=jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
