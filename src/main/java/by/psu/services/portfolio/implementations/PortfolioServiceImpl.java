package by.psu.services.portfolio.implementations;

import by.psu.database.entities.PortfolioItemEntity;
import by.psu.database.repositories.PortfolioCardRepository;
import by.psu.database.repositories.PortfolioItemRepository;
import by.psu.services.portfolio.interfaces.PortfolioService;
import by.psu.services.portfolio.mappers.PortfolioMapper;
import by.psu.services.portfolio.model.PortfolioCard;
import by.psu.services.portfolio.model.PortfolioItem;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PortfolioServiceImpl implements PortfolioService {
    private final PortfolioCardRepository portfolioCardRepository;
    private final PortfolioItemRepository portfolioItemRepository;
    private final PortfolioMapper portfolioMapper;

    public PortfolioServiceImpl(
            PortfolioCardRepository portfolioCardRepository,
            PortfolioMapper portfolioMapper,
            PortfolioItemRepository portfolioItemRepository) {
        this.portfolioCardRepository = portfolioCardRepository;
        this.portfolioMapper = portfolioMapper;
        this.portfolioItemRepository = portfolioItemRepository;
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

    @Override
    public List<PortfolioItem> savePortfolioItems(List<PortfolioItem> items) {
        List<PortfolioItemEntity> toSave = items
                .parallelStream()
                .map(portfolioMapper::toEntity)
                .collect(Collectors.toList());

        System.out.println("ORDER: " + items.get(0).getOrder());
        System.out.println(toSave.parallelStream().map(PortfolioItemEntity::getId).collect(Collectors.toList()));
        return portfolioItemRepository.saveAll(toSave)
                .parallelStream()
                .map(portfolioMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PortfolioItem> getPortfolioItems(String portfolioId) {
        return portfolioItemRepository.getAllByPortfolioCardId(portfolioId)
                .parallelStream()
                .map(portfolioMapper::toDto)
                .sorted((a, b) -> a.getOrder().compareTo(b.getOrder()))
                .collect(Collectors.toList());
    }
}
