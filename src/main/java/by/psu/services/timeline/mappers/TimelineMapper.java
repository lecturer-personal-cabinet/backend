package by.psu.services.timeline.mappers;

import by.psu.database.entities.UserTimelinePostEntity;
import by.psu.services.timeline.model.UserTimelinePost;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface TimelineMapper {
    @Mapping(source = "sender.id", target = "senderId")
    @Mapping(source = "sender.firstName", target = "senderFirstName")
    @Mapping(source = "sender.lastName", target = "senderLastName")
    UserTimelinePost toDto(UserTimelinePostEntity entity);

    @Mapping(source = "senderId", target = "sender.id")
    UserTimelinePostEntity toEntity(UserTimelinePost dto);
}
