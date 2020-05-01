package by.psu.services.portfolio.model;

import by.psu.services.users.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PortfolioCard {
    private String id;
    private String title;
    private String description;
    private String previewImageLink;
    private List<String> tags;
    private String userId;
}
