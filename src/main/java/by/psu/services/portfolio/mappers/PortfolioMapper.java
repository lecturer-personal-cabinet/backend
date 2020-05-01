package by.psu.services.portfolio.mappers;

import by.psu.database.entities.PortfolioCardEntity;
import by.psu.database.entities.UserEntity;
import by.psu.services.portfolio.model.PortfolioCard;
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
                .tags(List.of(entity.getTags()))
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
        entity.setTags(tags.toArray(new String[tags.size()]));
        entity.setTitle(card.getTitle());
        entity.setDescription(card.getDescription());
        entity.setUser(userEntity);

        return entity;
    }
}
