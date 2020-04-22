package by.psu.database.repositories;

import by.psu.database.entities.DialogParticipantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DialogParticipantRepository extends JpaRepository<DialogParticipantEntity, String> {
    List<DialogParticipantEntity> getAllByUserId(String userId);
}
