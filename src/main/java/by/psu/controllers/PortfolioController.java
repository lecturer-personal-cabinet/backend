package by.psu.controllers;

import by.psu.services.portfolio.interfaces.PortfolioService;
import by.psu.services.portfolio.model.PortfolioCard;
import by.psu.services.portfolio.model.PortfolioItem;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @GetMapping(path = "/portfolio/{portfolioId}/items",
            produces = "application/json",
            consumes = "application/json")
    public List<PortfolioItem> getPortfolioItems(@PathVariable("portfolioId") String portfolioId) {
        return portfolioService.getPortfolioItems(portfolioId);
    }

    @PostMapping(path = "/portfolio/items",
            produces = "application/json",
            consumes = "application/json")
    public List<PortfolioItem> savePortfolioItems(@RequestBody List<PortfolioItem> items) {
        return portfolioService.savePortfolioItems(items);
    }
}
