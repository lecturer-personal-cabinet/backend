package by.psu.controllers;

import by.psu.services.dialogs.model.Dialog;
import by.psu.services.users.interfaces.UsersService;
import by.psu.services.users.model.Group;
import by.psu.services.users.model.User;
import by.psu.services.users.model.UserProfile;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class UsersController {
    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping(path = "/users")
    public List<User> getAllUsers(@RequestParam Map<String, String> filters) {
        return usersService.getAllUsers(filters);
    }

    @GetMapping(path = "/groups/{groupId}/users")
    public List<User> getAllUsersRelatedToGroup(@PathVariable("groupId") String groupId) {
        return usersService.getAllUsersRelatedToGroup(groupId);
    }

    @GetMapping(path = "/users/{id}")
    public User getUserById(@PathVariable("id") String id) {
        return usersService
                .getUserById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find user"));
    }

    @GetMapping(path = "/groups")
    public List<Group> getAllGroups() {
        return usersService.getAllGroups();
    }

    @GetMapping(path = "/users/{userId}/groups")
    public Group getGroupRelatedToUser(@PathVariable("userId") String userId) {
        return usersService
                .getGroupRelatedToUser(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No group found"));
    }

    @GetMapping(path = "/groups/{id}")
    public Group getGroupById(@PathVariable("id") String id) {
        return usersService
                .getGroupById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find group by ID"));
    }

    @GetMapping(path = "/users/{userId}/profile")
    public UserProfile getUserProfile(@PathVariable("userId") String userId) {
        return usersService
                .getUserProfile(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find user profile"));
    }

    @PostMapping(path = "/users/{userId}/profile")
    public UserProfile saveUserProfile(@RequestBody UserProfile userProfile,
                                       @PathVariable("userId") String userId) {
        return usersService.saveUserProfile(userId, userProfile);
    }

    @PutMapping(path = "/users/{userId}/profile")
    public UserProfile updateUserProfile(@RequestBody UserProfile userProfile,
                                       @PathVariable("userId") String userId) {
        return usersService.saveUserProfile(userId, userProfile);
    }

    @PostMapping(path = "/users")
    public User saveUser(@RequestBody User user) {
        return usersService.saveUser(user);
    }

    @PutMapping(path = "/users/{userId}")
    public User updateUser(@RequestBody User user, @PathVariable("userId") String userId) {
        user.setId(userId);
        return usersService.saveUser(user);
    }

    @GetMapping(path = "/users/{userId}/dialogs")
    public List<Dialog> getUserDialogs(@PathVariable("userId") String userId) {
        return usersService.getUserDialogs(userId);
    }

    @GetMapping(path = "/users/{userId}/dialogs/count")
    public Integer getUserDialogsCount(@PathVariable("userId") String userId) {
        return usersService.getUserDialogs(userId).size();
    }
}
