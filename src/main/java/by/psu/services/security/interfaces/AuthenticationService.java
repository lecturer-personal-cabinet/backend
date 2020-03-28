package by.psu.services.security.interfaces;

import by.psu.services.security.model.LoginResponse;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface AuthenticationService {
    Optional<LoginResponse> googleLogin(String token);
}
