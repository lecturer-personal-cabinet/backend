package by.psu.database.repositories;

import by.psu.database.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<UserEntity, String> {
    List<UserEntity> findAllByGroup_Id(String groupId);
    Optional<UserEntity> findByEmail(String email);
}
