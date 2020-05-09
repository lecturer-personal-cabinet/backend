package by.psu.services.portfolio.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PortfolioItem {
    private String id;
    private PortfolioCard portfolioCard;
    private String type;
    private Integer order;
    private String metadata;
}
