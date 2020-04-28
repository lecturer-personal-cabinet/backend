package by.psu.services.portfolio.interfaces;

import by.psu.services.portfolio.model.PortfolioCard;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PortfolioService {
    PortfolioCard save(PortfolioCard dto);
    List<PortfolioCard> getByUserId(String userId);
}
