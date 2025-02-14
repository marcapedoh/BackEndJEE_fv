
package com.example.backend.auth;

import com.example.backend.dto.AuthenticationResponse;
import com.example.backend.dto.ChangePasswordRequest;
import io.swagger.annotations.Api;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.backend.constant.Utils.APP_ROOT;

@RestController
@CrossOrigin(origins = "*")
@Api(value = APP_ROOT + "user")
@RequestMapping(APP_ROOT + "user")
public class UserController {

    private final AuthenticationService authenticationService;

    public UserController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(value = "/change-password")
    public ResponseEntity<AuthenticationResponse> changedPassword(@Valid @RequestBody ChangePasswordRequest changedPasswordTemplate) {
        return ResponseEntity.ok(authenticationService.changedPassword(changedPasswordTemplate));
    }

}
