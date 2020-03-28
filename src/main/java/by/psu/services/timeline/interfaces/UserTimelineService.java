package by.psu.services.timeline.interfaces;

import by.psu.services.timeline.model.UserTimelinePost;

import java.util.List;

public interface UserTimelineService {
    List<UserTimelinePost> getAllUserTimelinePosts(String userId);
    UserTimelinePost saveUserTimelinePost(UserTimelinePost post);
}
