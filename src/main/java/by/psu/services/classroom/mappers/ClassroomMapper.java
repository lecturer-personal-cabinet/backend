package by.psu.services.classroom.mappers;

import by.psu.database.entities.ClassroomCodeAssignmentEntity;
import by.psu.database.entities.ClassroomCodeEntity;
import by.psu.services.classroom.model.ClassroomCode;
import by.psu.services.classroom.model.ClassroomCodeAssignment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface ClassroomMapper {
    @Mapping(source = "owner.id", target = "ownerId")
    ClassroomCode toDto(ClassroomCodeEntity entity);

    @Mapping(source = "ownerId", target = "owner.id")
    ClassroomCodeEntity toEntity(ClassroomCode dto);

    @Mapping(source = "classroomCode.id", target = "classroomCodeId")
    @Mapping(source = "group.id", target = "groupId")
    ClassroomCodeAssignment toDto(ClassroomCodeAssignmentEntity entity);

    @Mapping(source = "classroomCodeId", target = "classroomCode.id")
    @Mapping(source = "groupId", target = "group.id")
    ClassroomCodeAssignmentEntity toEntity(ClassroomCodeAssignment dto);
}
