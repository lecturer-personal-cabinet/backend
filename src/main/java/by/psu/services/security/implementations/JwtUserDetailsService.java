package by.psu.services.security.implementations;

import java.util.ArrayList;

import by.psu.services.users.interfaces.UsersService;
import by.psu.services.users.model.UserTokenData;
import by.psu.services.users.model.UserType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    private final UsersService usersService;
    private final PasswordEncoder passwordEncoder;

    public JwtUserDetailsService(UsersService usersService) {
        this.usersService = usersService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        by.psu.services.users.model.User user = usersService.getByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }

    public UserTokenData getUserTokenData(String username) throws UsernameNotFoundException {
        by.psu.services.users.model.User user = usersService.getByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

        return UserTokenData.builder().role(user.getType().name()).build();
    }

    public by.psu.services.users.model.User saveUser(by.psu.services.users.model.User user) {
        user.setType(UserType.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return usersService.saveUser(user);
    }
}