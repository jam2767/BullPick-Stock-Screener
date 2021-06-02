package com.bullpick.stock.screener.controllers;

import com.bullpick.stock.screener.models.Portfolio;
import com.bullpick.stock.screener.models.data.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
}
