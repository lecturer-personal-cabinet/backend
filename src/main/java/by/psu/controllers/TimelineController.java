package by.psu.controllers;

import by.psu.services.timeline.interfaces.UserTimelineService;
import by.psu.services.timeline.model.UserTimelinePost;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class TimelineController {
    private final UserTimelineService userTimelineService;

    public TimelineController(UserTimelineService userTimelineService) {
        this.userTimelineService = userTimelineService;
    }

    @GetMapping(path = "/users/{userId}/timeline/posts", produces = "application/json")
    public List<UserTimelinePost> getAllUserTimelinePosts(@PathVariable("userId") String userId) {
        return userTimelineService.getAllUserTimelinePosts(userId);
    }

    @PostMapping(path = "/users/{userId}/timeline/posts",
                produces = "application/json",
                consumes = "application/json")
    public UserTimelinePost saveUserTimelinePost(@RequestBody UserTimelinePost userTimelinePost,
                                                 @PathVariable("userId") String userId) {
        return userTimelineService.saveUserTimelinePost(userId, userTimelinePost);
    }
}
