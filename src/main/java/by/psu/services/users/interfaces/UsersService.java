package by.psu.services.users.interfaces;

import by.psu.services.dialogs.model.Dialog;
import by.psu.services.users.model.Group;
import by.psu.services.users.model.User;
import by.psu.services.users.model.UserProfile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public interface UsersService {
    List<User> getAllUsers(Map<String, String> filters);
    List<User> getAllUsersRelatedToGroup(String groupId);
    Optional<User> getUserById(String id);
    Optional<User> getByEmail(String email);
    User saveUser(User user);

    List<Group> getAllGroups();
    Optional<Group> getGroupRelatedToUser(String userId);
    Optional<Group> getGroupById(String id);

    Optional<UserProfile> getUserProfile(String userId);
    UserProfile saveUserProfile(String userId, UserProfile userProfile);

    List<Dialog> getUserDialogs(String userId);
}
