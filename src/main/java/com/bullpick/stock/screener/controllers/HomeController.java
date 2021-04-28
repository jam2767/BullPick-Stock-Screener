package com.bullpick.stock.screener.controllers;

import org.springframework.stereotype.Controller;

@Controller
public class HomeController {
    public String viewHomePage() {
        return "index";
    }
}
