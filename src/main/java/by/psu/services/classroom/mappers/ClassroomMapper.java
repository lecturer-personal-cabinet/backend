package by.psu.services.classroom.mappers;

import by.psu.database.entities.ClassroomCodeAssignmentEntity;
import by.psu.database.entities.ClassroomCodeEntity;
import by.psu.database.entities.UserEntity;
import by.psu.services.classroom.model.ClassroomCode;
import by.psu.services.classroom.model.ClassroomCodeAssignment;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
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

    @AfterMapping
    default ClassroomCodeEntity doAfterMapping(@MappingTarget ClassroomCodeEntity entity) {
        if(entity.getOwner().getId() == null)
            entity.setOwner(null);

        return entity;
    }

    @AfterMapping
    default ClassroomCodeAssignmentEntity doAfterMapping(@MappingTarget ClassroomCodeAssignmentEntity entity) {
        if(entity.getClassroomCode().getId() == null)
            entity.setClassroomCode(null);

        if(entity.getGroup().getId() == null)
            entity.setGroup(null);

        return entity;
    }
}
