package by.psu.services.portfolio.mappers;

import by.psu.database.entities.PortfolioCardEntity;
import by.psu.database.entities.PortfolioItemEntity;
import by.psu.database.entities.UserEntity;
import by.psu.services.portfolio.model.PortfolioCard;
import by.psu.services.portfolio.model.PortfolioItem;
import by.psu.services.users.mappers.UserMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PortfolioMapper {
    private final UserMapper userMapper;

    public PortfolioMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public PortfolioCard toDto(PortfolioCardEntity entity) {
        return PortfolioCard.builder()
                .id(entity.getId())
                .previewImageLink(entity.getPreviewImageLink())
                .tags(entity.getTags() == null ? List.of() : List.of(entity.getTags()))
                .title(entity.getTitle())
                .description(entity.getDescription())
                .userId(entity.getUser().getId())
                .build();
    }

    public PortfolioCardEntity toEntity(PortfolioCard card) {
        List<String> tags = card.getTags();
        var userEntity = new UserEntity();
        userEntity.setId(card.getUserId());

        var entity = new PortfolioCardEntity();
        entity.setId(card.getId());
        entity.setPreviewImageLink(card.getPreviewImageLink());
        if(tags != null) entity.setTags(tags.toArray(new String[tags.size()]));
        entity.setTitle(card.getTitle());
        entity.setDescription(card.getDescription());
        entity.setUser(userEntity);

        return entity;
    }

    public PortfolioItem toDto(PortfolioItemEntity entity) {
        return PortfolioItem.builder()
                .id(entity.getId())
                .type(entity.getType())
                .order(entity.getOrder())
                .metadata(entity.getMetadata())
                .portfolioCard(toDto(entity.getPortfolioCard()))
                .build();
    }

    public PortfolioItemEntity toEntity(PortfolioItem dto) {
        var entity = new PortfolioItemEntity();
        entity.setId(dto.getId());
        entity.setMetadata(dto.getMetadata());
        entity.setOrder(dto.getOrder());
        entity.setPortfolioCard(toEntity(dto.getPortfolioCard()));
        entity.setType(dto.getType());

        return entity;
    }
}
