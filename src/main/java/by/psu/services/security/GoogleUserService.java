package by.psu.services.security;

import by.psu.services.security.model.GoogleUser;
import org.springframework.stereotype.Component;

@Component
public interface GoogleUserService {
    GoogleUser save(GoogleUser googleUser);
}
