package by.psu.controllers;

import by.psu.services.security.GoogleUserService;
import by.psu.services.security.model.GoogleUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final GoogleUserService googleUserService;

    public UserController(GoogleUserService googleUserService) {
        this.googleUserService = googleUserService;
    }

    @PostMapping(path = "/user")
    public ResponseEntity<GoogleUser> save(@RequestBody GoogleUser googleUser) {
        return ResponseEntity.ok(googleUserService.save(googleUser));
    }
}
