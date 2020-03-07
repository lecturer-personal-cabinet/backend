package by.psu.services.security.impl;

import by.psu.database.entities.GoogleUserEntity;
import by.psu.database.repositories.GoogleUserRepository;
import by.psu.services.security.GoogleUserService;
import by.psu.services.security.SecurityMapper;
import by.psu.services.security.model.GoogleUser;
import org.springframework.stereotype.Service;

@Service
public class GoogleUserServiceImpl implements GoogleUserService {
    private final SecurityMapper securityMapper;
    private final GoogleUserRepository googleUserRepository;

    public GoogleUserServiceImpl(SecurityMapper securityMapper,
                                 GoogleUserRepository googleUserRepository) {
        this.securityMapper = securityMapper;
        this.googleUserRepository = googleUserRepository;
    }

    @Override
    public GoogleUser save(GoogleUser googleUser) {
        GoogleUserEntity entity = securityMapper.toEntity(googleUser);
        GoogleUserEntity savedEntity = googleUserRepository.save(entity);
        return securityMapper.toDto(savedEntity);
    }
}
