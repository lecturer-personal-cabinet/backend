package by.psu.database.repositories;

import by.psu.database.entities.DialogMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface DialogMessageRepository extends JpaRepository<DialogMessageEntity, String> {
    List<DialogMessageEntity> getAllByDialogId(String dialogId);

    @Modifying
    @Query("UPDATE DialogMessageEntity msg SET msg.isRead = :status WHERE msg.dialog.id = :dialogId  AND msg.sender.id not like :exclude")
    @Transactional
    int updateStatusInAllMessages(@Param("dialogId") String dialogId, @Param("status") Boolean status, @Param("exclude") String exclude);
}
