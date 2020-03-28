package by.psu.services.security.implementations;

import by.psu.configuration.SecurityConfiguration;
import by.psu.services.security.interfaces.AuthenticationService;
import by.psu.services.security.mappers.SecurityMapper;
import by.psu.services.security.model.LoginResponse;
import by.psu.services.users.interfaces.UsersService;
import by.psu.services.users.model.User;
import by.psu.services.users.model.UserType;
import by.psu.utils.JwtUtils;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final SecurityMapper securityMapper;
    private final UsersService usersService;
    private final GoogleIdTokenVerifier verifier;
    private final JwtUtils jwtUtils;

    public AuthenticationServiceImpl(UsersService usersService,
                                     SecurityMapper securityMapper,
                                     SecurityConfiguration securityConfiguration,
                                     JwtUtils jwtUtils) {
        this.usersService = usersService;
        this.securityMapper = securityMapper;
        this.verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new JacksonFactory())
                .setAudience(Collections.singletonList(securityConfiguration.getGoogleClientId()))
                .build();
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Optional<LoginResponse> googleLogin(String token) {
        try {
            GoogleIdToken idToken = verifier.verify(token);
            User user = this.getOrCreateUser(idToken.getPayload());
            String jwtToken = jwtUtils.generateToken(user);
            return Optional.of(securityMapper.toResponse(jwtToken, user.getId()));
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            return Optional.empty();
        }
    }

    @Override
    public Boolean isTokenValid(String token) {
        try {
            String userId = jwtUtils.getIdFromToken(token);
            return usersService.getUserById(userId)
                    .map(user -> jwtUtils.validateToken(token, user))
                    .orElse(false);
        } catch(Exception e) {
            System.out.println("IsTokenValid: " + Arrays.toString(e.getStackTrace()));
            return false;
        }
    }

    @Override
    public Optional<User> getUserFromToken(String token) {
        try {
            String userId = jwtUtils.getIdFromToken(token);
            return usersService.getUserById(userId);
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            return Optional.empty();
        }
    }

    private User getOrCreateUser(GoogleIdToken.Payload googleUserPayload) {
        Optional<User> maybeUser = usersService.getByEmail(googleUserPayload.getEmail());

        if(maybeUser.isEmpty()) {
            User user = new User();
            user.setType(UserType.UNKNOWN);
            user.setFirstName((String) googleUserPayload.get("given_name"));
            user.setLastName((String) googleUserPayload.get("family_name"));
            user.setEmail(googleUserPayload.getEmail());
            return usersService.saveUser(user);
        } else {
            return maybeUser.get();
        }
    }
}
