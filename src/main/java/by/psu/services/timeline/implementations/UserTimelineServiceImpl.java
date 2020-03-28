package by.psu.services.timeline.implementations;

import by.psu.database.entities.UserTimelinePostEntity;
import by.psu.database.repositories.UserTimelinePostRepository;
import by.psu.services.timeline.interfaces.UserTimelineService;
import by.psu.services.timeline.mappers.TimelineMapper;
import by.psu.services.timeline.model.UserTimelinePost;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserTimelineServiceImpl implements UserTimelineService {
    private final UserTimelinePostRepository userTimelinePostRepository;
    private final TimelineMapper timelineMapper;

    public UserTimelineServiceImpl(UserTimelinePostRepository userTimelinePostRepository,
                                   TimelineMapper timelineMapper) {
        this.userTimelinePostRepository = userTimelinePostRepository;
        this.timelineMapper = timelineMapper;
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
        return timelineMapper.toDto(savedEntity);
    }
}
