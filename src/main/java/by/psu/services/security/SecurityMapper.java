package by.psu.services.security;

import by.psu.database.entities.GoogleUserEntity;
import by.psu.services.security.model.GoogleUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SecurityMapper {
    GoogleUser toDto(GoogleUserEntity googleUserEntity);
    GoogleUserEntity toEntity(GoogleUser googleUser);
}
