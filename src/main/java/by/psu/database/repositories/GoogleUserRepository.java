package by.psu.database.repositories;

import by.psu.database.entities.GoogleUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoogleUserRepository extends JpaRepository<GoogleUserEntity, String> { }
