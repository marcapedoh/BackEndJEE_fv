
package com.example.backend.auth;


import com.example.backend.dto.AuthenticationRequest;
import com.example.backend.dto.AuthenticationResponse;
import com.example.backend.dto.ChangePasswordRequest;
import com.example.backend.dto.RegisterRequest;
import io.swagger.annotations.Api;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.backend.constant.Utils.APP_ROOT;

@RestController
@CrossOrigin(origins = "*")
@Api(value = APP_ROOT + "auth")
@RequestMapping(APP_ROOT + "auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping(value = "/user/change-password")
    public ResponseEntity<AuthenticationResponse> changedPassword(@Valid @RequestBody ChangePasswordRequest changedPasswordTemplate) {
        return ResponseEntity.ok(authenticationService.changedPassword(changedPasswordTemplate));
    }

}
