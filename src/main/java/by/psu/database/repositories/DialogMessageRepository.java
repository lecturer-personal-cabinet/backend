package by.psu.database.repositories;

import by.psu.database.entities.DialogMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DialogMessageRepository extends JpaRepository<DialogMessageEntity, String> {
    List<DialogMessageEntity> getAllByDialogId(String dialogId);
}
