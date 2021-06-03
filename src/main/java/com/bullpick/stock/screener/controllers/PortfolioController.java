package com.bullpick.stock.screener.controllers;

import com.bullpick.stock.screener.models.Portfolio;
import com.bullpick.stock.screener.models.data.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class PortfolioController {

    @Autowired
    private PortfolioRepository portfolioRepository;

    //display add portfolio form
    @GetMapping("portfolio/add")
    //@CrossOrigin
    public String displayAddPortfolioForm(Model model) {
        model.addAttribute(new Portfolio());
        return "add_portfolio";
    }

    //process add portfolio form
    @PostMapping("portfolio/add")
    public String processAddPortfolioForm(@ModelAttribute Portfolio newPortfolio, Model model) {
        portfolioRepository.save(newPortfolio);

        return "redirect:../home";
    }

    //detailed view of portfolio
    @GetMapping("portfolio/detail")
    public String displayPortfolioDetails(@RequestParam Integer portfolioId, Model model) {
        Optional<Portfolio> result = portfolioRepository.findById(portfolioId);

        if (result.isEmpty()) {
            model.addAttribute("title", "Invalid Portfolio ID: " + portfolioId);
        } else {
            Portfolio portfolio = result.get();
            model.addAttribute("title", portfolio.getName() + "Details");
            model.addAttribute("portfolio", portfolio);
            model.addAttribute("stocks", portfolio.getStocks());
        }

        return "portfolio_detail";
    }
}
