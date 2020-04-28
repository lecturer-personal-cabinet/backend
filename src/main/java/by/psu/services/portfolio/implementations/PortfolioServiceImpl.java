package by.psu.services.portfolio.implementations;

import by.psu.database.repositories.PortfolioCardRepository;
import by.psu.services.portfolio.interfaces.PortfolioService;
import by.psu.services.portfolio.mappers.PortfolioMapper;
import by.psu.services.portfolio.model.PortfolioCard;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PortfolioServiceImpl implements PortfolioService {
    private final PortfolioCardRepository portfolioCardRepository;
    private final PortfolioMapper portfolioMapper;

    public PortfolioServiceImpl(PortfolioCardRepository portfolioCardRepository, PortfolioMapper portfolioMapper) {
        this.portfolioCardRepository = portfolioCardRepository;
        this.portfolioMapper = portfolioMapper;
    }

    @Override
    public PortfolioCard save(PortfolioCard dto) {
        var entity = portfolioMapper.toEntity(dto);
        var result = portfolioCardRepository.save(entity);

        return portfolioMapper.toDto(result);
    }

    @Override
    public List<PortfolioCard> getByUserId(String userId) {
        return portfolioCardRepository
                .getAllByUserId(userId)
                .parallelStream()
                .map(portfolioMapper::toDto)
                .collect(Collectors.toList());
    }
}