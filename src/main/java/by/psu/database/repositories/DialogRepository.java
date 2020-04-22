package by.psu.database.repositories;

import by.psu.database.entities.DialogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DialogRepository extends JpaRepository<DialogEntity, String> {
}
