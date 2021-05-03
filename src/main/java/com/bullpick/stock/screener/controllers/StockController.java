package com.bullpick.stock.screener.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClient;

@Controller
public class StockController {

    @Autowired
    private WebClient.Builder webClientBuilder;

    private String apiKey = System.getenv("API_KEY");

    @GetMapping("/stock")
    @ResponseBody
    public String envDetails() {

        String stockName = webClientBuilder.build()
                .get()
                .uri("https://finnhub.io/api/v1/search?q=apple&" + "token=" + apiKey)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return stockName;
    }

}
