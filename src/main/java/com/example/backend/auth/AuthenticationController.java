
package com.example.backend.auth;



import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.backend.constant.Utils.APP_ROOT;

@RestController
@CrossOrigin(origins = "*")
@Api(value = APP_ROOT+"auth" )
@RequestMapping(APP_ROOT+"auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    public AuthenticationController(AuthenticationService authenticationService){
        this.authenticationService=authenticationService;
    }
    @PostMapping(value = "/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
    @PostMapping(value = "/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authenticationService.register(request));
    }

}
