package by.psu.database.repositories;

import by.psu.database.entities.ClassroomCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassroomCodesRepository extends JpaRepository<ClassroomCodeEntity, String> {
    List<ClassroomCodeEntity> findAllByOwnerId(String ownerId);
}
