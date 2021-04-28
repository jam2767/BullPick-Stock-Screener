package com.bullpick.stock.screener.controllers;

import com.bullpick.stock.screener.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/register")
    public String displayRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registration_form";
    }
}
