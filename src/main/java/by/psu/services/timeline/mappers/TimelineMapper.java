package by.psu.services.timeline.mappers;

import by.psu.database.entities.UserEntity;
import by.psu.database.entities.UserTimelinePostEntity;
import by.psu.services.timeline.model.UserTimelinePost;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
@Component
public interface TimelineMapper {
    @Mapping(source = "sender.firstName", target = "senderFirstName")
    @Mapping(source = "sender.lastName", target = "senderLastName")
    UserTimelinePost toDto(UserTimelinePostEntity entity);

    @Mapping(source = "userId", target = "sender.id")
    UserTimelinePostEntity toEntity(UserTimelinePost dto, String userId);

    default Timestamp map(LocalDateTime localDateTime) {
        return localDateTime == null ? null : Timestamp.valueOf(localDateTime);
    }

    default LocalDateTime map(Timestamp ts) {
        return ts == null ? null : ts.toLocalDateTime();
    }

    @AfterMapping
    default UserTimelinePostEntity doAfterMapping(@MappingTarget UserTimelinePostEntity entity) {
        if (entity.getSender() != null && entity.getSender().getId() == null)
            entity.setSender(null);

        return entity;
    }
}
