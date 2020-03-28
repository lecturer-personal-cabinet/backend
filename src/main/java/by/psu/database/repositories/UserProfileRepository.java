package by.psu.database.repositories;

import by.psu.database.entities.UserProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfileEntity, String> {
    Optional<UserProfileEntity> findByUser_Id(String userId);
}
