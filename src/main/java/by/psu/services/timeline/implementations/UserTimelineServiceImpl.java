package by.psu.services.timeline.implementations;

import by.psu.database.entities.UserTimelinePostEntity;
import by.psu.database.repositories.UserTimelinePostRepository;
import by.psu.services.timeline.interfaces.UserTimelineService;
import by.psu.services.timeline.mappers.TimelineMapper;
import by.psu.services.timeline.model.UserTimelinePost;
import by.psu.services.users.interfaces.UsersService;
import by.psu.services.users.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserTimelineServiceImpl implements UserTimelineService {
    private final UserTimelinePostRepository userTimelinePostRepository;
    private final UsersService usersService;
    private final TimelineMapper timelineMapper;

    public UserTimelineServiceImpl(UserTimelinePostRepository userTimelinePostRepository,
                                   TimelineMapper timelineMapper,
                                   UsersService usersService) {
        this.userTimelinePostRepository = userTimelinePostRepository;
        this.timelineMapper = timelineMapper;
        this.usersService = usersService;
    }

    @Override
    public List<UserTimelinePost> getAllUserTimelinePosts(String userId) {
        return userTimelinePostRepository
                .findAllBySender_Id(userId)
                .stream()
                .map(timelineMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserTimelinePost saveUserTimelinePost(String userId, UserTimelinePost post) {
        UserTimelinePostEntity entityToSave = timelineMapper.toEntity(post, userId);
        UserTimelinePostEntity savedEntity = userTimelinePostRepository.save(entityToSave);
        Optional<User> user = usersService.getUserById(userId);
        UserTimelinePost savedDto = timelineMapper.toDto(savedEntity);

        user.ifPresent(value -> {
            savedDto.setSenderFirstName(value.getFirstName());
            savedDto.setSenderLastName(value.getLastName());
        });

        return savedDto;
    }
}
