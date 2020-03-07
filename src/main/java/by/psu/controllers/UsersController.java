package by.psu.controllers;

import by.psu.services.users.interfaces.UsersService;
import by.psu.services.users.model.Group;
import by.psu.services.users.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
public class UsersController {
    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping(path = "/users")
    public List<User> getAllUsers() {
        return usersService.getAllUsers();
    }

    @GetMapping(path = "/groups/users/{groupId}")
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

    @GetMapping(path = "/users/groups/{userId}")
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
}
