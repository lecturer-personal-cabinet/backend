package by.psu.database.repositories;

import by.psu.database.entities.PortfolioItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PortfolioItemRepository extends JpaRepository<PortfolioItemEntity, String> {
    List<PortfolioItemEntity> getAllByPortfolioCardId(String portfolioCardId);
    int deleteAllById(String[] ids);
}
