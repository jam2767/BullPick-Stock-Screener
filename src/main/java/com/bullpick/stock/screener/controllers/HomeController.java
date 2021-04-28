package com.bullpick.stock.screener.controllers;

import com.bullpick.stock.screener.User;
import com.bullpick.stock.screener.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @Autowired
    private UserRepository repo;

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
        repo.save(user);

        return "registration_success";
    }
}
