package by.psu.services.security.mappers;

import by.psu.services.security.model.LoginResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface SecurityMapper {
    LoginResponse toResponse(String jwt, String userId);
}
