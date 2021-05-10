package com.bullpick.stock.screener.controllers;


import com.bullpick.stock.screener.models.Stock;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.bytebuddy.description.method.MethodDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClient;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Controller
public class StockController {

    @Autowired
    private WebClient.Builder webClientBuilder;

    private String apiKey = System.getenv("API_KEY");

    @GetMapping("/stock")
    @ResponseBody
    public String envDetails() {

        String stockName = webClientBuilder
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(2 * 1024 * 1024))
                .build()
                .get()
                .uri("https://finnhub.io/api/v1/search?q=apple&" + "token=" + apiKey)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return stockName;
    }

   @GetMapping("/list")
   @ResponseBody
    public List stockDetails() {
        String nyseStockInformation = webClientBuilder
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(2 * 1024 * 1024))
                .build()
                .get()
                .uri("https://finnhub.io/api/v1/stock/symbol?exchange=US&mic=XNYS&token=" + apiKey)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        Gson gsonNyseStock = new Gson();

        Type nyseStockInformationListType = new TypeToken<ArrayList<Stock>>(){}.getType();

       List<Stock> nyseStockList = gsonNyseStock.fromJson(nyseStockInformation, nyseStockInformationListType);

       return nyseStockList;

   }


}
