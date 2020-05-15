package by.psu.services.users.mappers;

import by.psu.database.entities.GroupEntity;
import by.psu.database.entities.UserEntity;
import by.psu.database.entities.UserProfileEntity;
import by.psu.services.users.model.Group;
import by.psu.services.users.model.User;
import by.psu.services.users.model.UserProfile;
import by.psu.services.users.model.UserType;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity toEntity(User dto) {
        GroupEntity groupEntity = null;
        if (dto.getGroupId() != null) {
            groupEntity = new GroupEntity();
            groupEntity.setId(dto.getId());
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setId(dto.getId());
        userEntity.setFirstName(dto.getFirstName());
        userEntity.setLastName(dto.getLastName());
        userEntity.setPatronymic(dto.getPatronymic());
        userEntity.setEmail(dto.getEmail());
        userEntity.setType(dto.getType() == null ? UserType.USER.name() : dto.getType().name());
        userEntity.setPassword(dto.getPassword());
        userEntity.setImage(dto.getImage());
        userEntity.setGroup(groupEntity);

        return userEntity;
    }

    public User toDto(UserEntity entity) {
        User user = new User();
        user.setId(entity.getId());
        user.setFirstName(entity.getFirstName());
        user.setLastName(entity.getLastName());
        user.setPatronymic(entity.getPatronymic());
        user.setEmail(entity.getEmail());
        user.setGroupId(entity.getGroup() == null ? null : entity.getGroup().getId());
        user.setImage(entity.getImage());
        user.setType(UserType.valueOf(entity.getType()));
        user.setPassword(entity.getPassword());

        return user;
    }

    public GroupEntity toEntity(Group dto) {
        GroupEntity entity = new GroupEntity();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());

        return entity;
    }

    public Group toDto(GroupEntity entity) {
        Group group = new Group();
        group.setId(entity.getId());
        group.setTitle(entity.getTitle());
        return group;
    }

    public UserProfile toDto(UserProfileEntity entity) {
        UserProfile dto = new UserProfile();
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setTimezone(entity.getTimezone());
        dto.setAddress(entity.getAddress());
        dto.setPhoneNumber(entity.getPhoneNumber());

        return dto;
    }

    public UserProfileEntity toEntity(UserProfile dto, String userId) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);

        UserProfileEntity entity = new UserProfileEntity();
        entity.setId(dto.getId());
        entity.setUser(userEntity);
        entity.setDescription(dto.getDescription());
        entity.setTimezone(dto.getTimezone());
        entity.setAddress(dto.getAddress());
        entity.setPhoneNumber(dto.getPhoneNumber());

        return entity;
    }
}
