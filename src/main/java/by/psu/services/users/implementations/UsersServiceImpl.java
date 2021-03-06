package by.psu.services.users.implementations;

import by.psu.database.entities.UserEntity;
import by.psu.database.entities.UserProfileEntity;
import by.psu.database.repositories.GroupsRepository;
import by.psu.database.repositories.UserProfileRepository;
import by.psu.database.repositories.UsersRepository;
import by.psu.services.dialogs.interfaces.DialogsService;
import by.psu.services.dialogs.model.Dialog;
import by.psu.services.portfolio.interfaces.PortfolioService;
import by.psu.services.portfolio.model.PortfolioCard;
import by.psu.services.users.interfaces.UsersService;
import by.psu.services.users.mappers.UserMapper;
import by.psu.services.users.model.Group;
import by.psu.services.users.model.User;
import by.psu.services.users.model.UserProfile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UsersService {
    private final UserMapper userMapper;
    private final UsersRepository usersRepository;
    private final GroupsRepository groupsRepository;
    private final UserProfileRepository userProfileRepository;
    private final PortfolioService portfolioService;

    public UsersServiceImpl(UserMapper userMapper,
                            UsersRepository usersRepository,
                            GroupsRepository groupsRepository,
                            UserProfileRepository userProfileRepository,
                            PortfolioService portfolioService) {
        this.userMapper = userMapper;
        this.usersRepository = usersRepository;
        this.groupsRepository = groupsRepository;
        this.userProfileRepository = userProfileRepository;
        this.portfolioService = portfolioService;
    }

    @Override
    public List<User> getAllUsers(Map<String, String> filters) {
        List<User> users = usersRepository
                .findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());

        return filterUsers(users, filters);
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
    public User saveUser(User user) {
        UserEntity entityToSave = userMapper.toEntity(user);
        UserEntity savedEntity = usersRepository.save(entityToSave);
        return userMapper.toDto(savedEntity);
    }

    @Override
    public User updateUser(User user) {
        User existing = this.getUserById(user.getId()).orElseThrow();
        user.setPassword(existing.getPassword());
        return this.saveUser(user);
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return usersRepository
                .findByEmail(email)
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

    @Override
    public Optional<UserProfile> getUserProfile(String userId) {
        return userProfileRepository
                .findByUser_Id(userId)
                .map(userMapper::toDto);
    }

    @Override
    public UserProfile saveUserProfile(String userId, UserProfile userProfile) {
        Optional<UserProfileEntity> maybeExistingProfile = userProfileRepository.findByUser_Id(userId);

        UserProfileEntity entityToSave = userMapper.toEntity(userProfile, userId);
        UserEntity userEntity = usersRepository.findById(userId).orElseThrow();
        entityToSave.setUser(userEntity);
        maybeExistingProfile.ifPresent(entity -> entityToSave.setId(entity.getId()));
        UserProfileEntity savedEntity = userProfileRepository.save(entityToSave);
        return userMapper.toDto(savedEntity);
    }

    @Override
    public List<PortfolioCard> getUserPortfolioCards(String userId) {
        return portfolioService.getByUserId(userId);
    }

    private List<User> filterUsers(List<User> users, Map<String, String> filters) {
        String searchValue = filters.getOrDefault("search", "");

        return users
                .stream()
                .filter(user -> user.getFirstName().toLowerCase().contains(searchValue.toLowerCase()) ||
                        user.getLastName().toLowerCase().contains(searchValue.toLowerCase()))
                .collect(Collectors.toList());
    }
}
