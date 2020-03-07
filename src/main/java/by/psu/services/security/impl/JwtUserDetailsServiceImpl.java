package by.psu.services.security.impl;

import by.psu.database.entities.GoogleUserEntity;
import by.psu.database.repositories.GoogleUserRepository;
import by.psu.services.security.JwtUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class JwtUserDetailsServiceImpl implements JwtUserDetailsService {
    private GoogleUserRepository googleUserRepository;

    public JwtUserDetailsServiceImpl(GoogleUserRepository googleUserRepository) {
        this.googleUserRepository = googleUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Optional<GoogleUserEntity> maybeGoogleUserEntity = googleUserRepository.findById(id);
        
        if(maybeGoogleUserEntity.isEmpty())
            throw new UsernameNotFoundException("User not found with id: " + id);

        GoogleUserEntity googleUserEntity = maybeGoogleUserEntity.get();
        return new User(googleUserEntity.getId(), googleUserEntity.getToken(), new ArrayList<>());
    }
}
