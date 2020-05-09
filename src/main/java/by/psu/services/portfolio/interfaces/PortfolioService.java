package by.psu.services.portfolio.interfaces;

import by.psu.services.portfolio.model.PortfolioCard;
import by.psu.services.portfolio.model.PortfolioItem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PortfolioService {
    PortfolioCard save(PortfolioCard dto);
    List<PortfolioCard> getByUserId(String userId);

    List<PortfolioItem> savePortfolioItems(List<PortfolioItem> items);
    List<PortfolioItem> getPortfolioItems(String portfolioId);
}
