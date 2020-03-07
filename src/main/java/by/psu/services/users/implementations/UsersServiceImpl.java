package by.psu.services.users.implementations;

import by.psu.database.entities.UserEntity;
import by.psu.database.repositories.GroupsRepository;
import by.psu.database.repositories.UsersRepository;
import by.psu.services.users.interfaces.UsersService;
import by.psu.services.users.mappers.UserMapper;
import by.psu.services.users.model.Group;
import by.psu.services.users.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UsersService {
    private final UserMapper userMapper;
    private final UsersRepository usersRepository;
    private final GroupsRepository groupsRepository;

    public UsersServiceImpl(UserMapper userMapper,
                            UsersRepository usersRepository,
                            GroupsRepository groupsRepository) {
        this.userMapper = userMapper;
        this.usersRepository = usersRepository;
        this.groupsRepository = groupsRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return usersRepository
                .findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getAllUsersRelatedToGroup(String groupId) {
        return usersRepository
                .findAllByGroup_Id(groupId)
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> getUserById(String id) {
        return usersRepository
                .findById(id)
                .map(userMapper::toDto);
    }

    @Override
    public List<Group> getAllGroups() {
        return groupsRepository
                .findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Group> getGroupRelatedToUser(String userId) {
        return usersRepository
                .findById(userId)
                .map(UserEntity::getGroup)
                .map(userMapper::toDto);
    }

    @Override
    public Optional<Group> getGroupById(String id) {
        return groupsRepository
                .findById(id)
                .map(userMapper::toDto);
    }
}
