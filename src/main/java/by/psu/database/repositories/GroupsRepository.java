package by.psu.database.repositories;

import by.psu.database.entities.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupsRepository extends JpaRepository<GroupEntity, String> {
}
