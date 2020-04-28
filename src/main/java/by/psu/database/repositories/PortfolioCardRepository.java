package by.psu.database.repositories;

import by.psu.database.entities.PortfolioCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PortfolioCardRepository extends JpaRepository<PortfolioCardEntity, String> {
    List<PortfolioCardEntity> getAllByUserId(String userId);
}
