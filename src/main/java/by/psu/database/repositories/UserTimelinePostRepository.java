package by.psu.database.repositories;

import by.psu.database.entities.UserTimelinePostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTimelinePostRepository extends JpaRepository<UserTimelinePostEntity, String> {
    List<UserTimelinePostEntity> findAllBySender_Id(String senderId);
}
