package com.example.backend.controller;

import com.example.backend.model.ChangePasswordRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.example.backend.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private com.example.backend.service.AuthService authService;
//
       @Autowired
      private com.example.backend.service.UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody com.example.backend.model.User loginRequest) {
       /* Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = authService.generateToken(authentication);*/
        return ResponseEntity.ok("null" /*new JwtResponse(jwt)*/);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody com.example.backend.model.User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("Logout successful");
    }

    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request) {
       // return ResponseEntity.ok(authService.changePassword(request));
        return ResponseEntity.ok("null");
    }
}
