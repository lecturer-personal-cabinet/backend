package by.psu.database.repositories;

import by.psu.database.entities.ClassroomCodeAssignmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassroomCodeAssignmentsRepository extends JpaRepository<ClassroomCodeAssignmentEntity, String> {
    List<ClassroomCodeAssignmentEntity> getAllByGroup_Id(String groupId);
}
