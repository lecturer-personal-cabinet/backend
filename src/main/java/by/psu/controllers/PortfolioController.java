package by.psu.controllers;

import by.psu.services.portfolio.interfaces.PortfolioService;
import by.psu.services.portfolio.model.PortfolioCard;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class PortfolioController {
    private final PortfolioService portfolioService;

    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @PostMapping(path = "/portfolio/card",
            produces = "application/json",
            consumes = "application/json")
    public PortfolioCard savePortfolioCard(@RequestBody PortfolioCard portfolioCard) {
        return portfolioService.save(portfolioCard);
    }
}
