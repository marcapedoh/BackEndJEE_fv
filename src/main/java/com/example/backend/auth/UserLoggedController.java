
package com.example.backend.auth;

import com.example.backend.dto.AuthenticationResponse;
import com.example.backend.dto.ChangePasswordRequest;
import com.example.backend.dto.UpdateUserRequest;
import com.example.backend.model.User;
import io.swagger.annotations.Api;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.backend.constant.Utils.APP_ROOT;

@RestController
@CrossOrigin(origins = "*")
@Api(value = APP_ROOT + "user")
@RequestMapping(APP_ROOT + "user")
public class UserLoggedController {

    private final AuthenticationService authenticationService;

    public UserLoggedController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PutMapping(value = "/update")
    public ResponseEntity<User> updateUser(@Valid @RequestBody UpdateUserRequest updateUserRequest) {
        return ResponseEntity.ok(authenticationService.updateUser(updateUserRequest));
    }

    @GetMapping(value = "/me")
    public ResponseEntity<User> getAuthenticatedUser() {
        return ResponseEntity.ok(authenticationService.getAuthenticatedUser());
    }

    @PutMapping(value = "/change-password")
    public ResponseEntity<AuthenticationResponse> changedPassword(@Valid @RequestBody ChangePasswordRequest changedPasswordTemplate) {
        return ResponseEntity.ok(authenticationService.changedPassword(changedPasswordTemplate));
    }

}
