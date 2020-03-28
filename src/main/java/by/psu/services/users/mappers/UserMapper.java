package by.psu.services.users.mappers;

import by.psu.database.entities.GroupEntity;
import by.psu.database.entities.UserEntity;
import by.psu.database.entities.UserProfileEntity;
import by.psu.services.users.model.Group;
import by.psu.services.users.model.User;
import by.psu.services.users.model.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface UserMapper {
    @Mapping(source = "groupId", target = "group.id")
    UserEntity toEntity(User dto);
    @Mapping(source = "group.id", target = "groupId")
    User toDto(UserEntity entity);

    GroupEntity toEntity(Group dto);
    Group toDto(GroupEntity entity);

    UserProfile toDto(UserProfileEntity entity);
    @Mapping(source = "userId", target = "user.id")
    UserProfileEntity toEntity(UserProfile dto, String userId);
}
