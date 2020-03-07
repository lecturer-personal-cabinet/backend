package by.psu.services.users.interfaces;

import by.psu.services.users.model.Group;
import by.psu.services.users.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface UsersService {
    List<User> getAllUsers();
    List<User> getAllUsersRelatedToGroup(String groupId);
    Optional<User> getUserById(String id);

    List<Group> getAllGroups();
    Optional<Group> getGroupRelatedToUser(String userId);
    Optional<Group> getGroupById(String id);
}
