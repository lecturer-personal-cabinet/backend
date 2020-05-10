package by.psu.services.users.mappers;

import by.psu.database.entities.GroupEntity;
import by.psu.database.entities.UserEntity;
import by.psu.database.entities.UserProfileEntity;
import by.psu.services.users.model.Group;
import by.psu.services.users.model.User;
import by.psu.services.users.model.UserProfile;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
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

    @AfterMapping
    default UserEntity doAfterMapping(@MappingTarget UserEntity entity) {
        try {
            if (entity.getGroup().getId() == null)
                entity.setGroup(null);
        } catch(Exception e) {
            entity.setGroup(null);
        }

        return entity;
    }

    @AfterMapping
    default UserProfileEntity doAfterMapping(@MappingTarget UserProfileEntity entity) {
        if (entity.getUser() != null && entity.getUser().getId() == null)
            entity.setUser(null);

        return entity;
    }
}
