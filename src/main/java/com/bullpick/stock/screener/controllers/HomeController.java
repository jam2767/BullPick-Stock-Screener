package com.bullpick.stock.screener.controllers;

import com.bullpick.stock.screener.models.Portfolio;
import com.bullpick.stock.screener.models.User;
import com.bullpick.stock.screener.models.data.PortfolioRepository;
import com.bullpick.stock.screener.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private UserRepository repo;

    @Autowired
    private PortfolioRepository portfolioRepository;

//    @GetMapping
//    private String displayAllPortfolios(Model model) {
//        model.addAttribute("portfolios", po)
//    }

    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/register")
    public String displayRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registration_form";
    }

    @PostMapping("/process_registration")
    public String processUserRegistration(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        repo.save(user);

        return "registration_success";
    }

    //displays all portfolios
    @GetMapping("/home")
    public String viewUserHome(Model model) {
    model.addAttribute("portfolios",portfolioRepository.findAll());
        return "home";
    }

    //delete a portfolio
    @RequestMapping("/home/{id}")
    public String deletePortfolio(@PathVariable int id) {
        Optional<Portfolio> portfolioToDelete = portfolioRepository.findById(id);
        if(portfolioToDelete.isPresent()) {
            portfolioRepository.delete(portfolioToDelete.get());
            return "redirect:../home";
        } else {
            throw new RuntimeException("Portfolio not found");
        }

    }
}
