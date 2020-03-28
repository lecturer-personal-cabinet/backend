package by.psu.controllers;

import by.psu.services.security.interfaces.AuthenticationService;
import by.psu.services.security.model.GoogleLoginRequest;
import by.psu.services.security.model.LoginResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(path = "/login/google")
    public LoginResponse googleLogin(@RequestBody GoogleLoginRequest googleLoginRequest) {
        return authenticationService
        .googleLogin(googleLoginRequest.getTokenId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Google unauthorized"));
    }
}
