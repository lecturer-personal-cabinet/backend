package by.psu.database.repositories;

import by.psu.database.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<UserEntity, String> {
    List<UserEntity> findAllByGroup_Id(String groupId);
}
