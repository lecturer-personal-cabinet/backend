package by.psu.services.security.implementations;

import by.psu.configuration.SecurityConfiguration;
import by.psu.services.security.interfaces.AuthenticationService;
import by.psu.services.security.mappers.SecurityMapper;
import by.psu.services.security.model.LoginResponse;
import by.psu.services.users.interfaces.UsersService;
import by.psu.services.users.model.User;
import by.psu.services.users.model.UserType;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final SecurityMapper securityMapper;
    private final UsersService usersService;
    private final SecurityConfiguration securityConfiguration;

    public AuthenticationServiceImpl(UsersService usersService,
                                     SecurityMapper securityMapper,
                                     SecurityConfiguration securityConfiguration) {
        this.usersService = usersService;
        this.securityMapper = securityMapper;
        this.securityConfiguration = securityConfiguration;
    }

    @Override
    public Optional<LoginResponse> googleLogin(String token) {
        try {
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new JacksonFactory())
                    .setAudience(Collections.singletonList(securityConfiguration.getGoogleClientId()))
                    .build();
            GoogleIdToken idToken = verifier.verify(token);
            String userId = this.getOrCreateUser(idToken.getPayload());
            return Optional.of(securityMapper.toResponse(token, userId));
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            return Optional.empty();
        }
    }

    private String getOrCreateUser(GoogleIdToken.Payload googleUserPayload) {
        Optional<User> maybeUser = usersService.getByEmail(googleUserPayload.getEmail());

        if(maybeUser.isEmpty()) {
            User user = new User();
            user.setType(UserType.FULL_TIME_STUDENT);
            user.setFirstName((String) googleUserPayload.get("given_name"));
            user.setLastName((String) googleUserPayload.get("family_name"));
            user.setEmail(googleUserPayload.getEmail());
            return usersService.saveUser(user).getId();
        } else {
            return maybeUser.get().getId();
        }
    }
}
