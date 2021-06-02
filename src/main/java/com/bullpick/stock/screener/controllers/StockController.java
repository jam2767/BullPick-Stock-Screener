package com.bullpick.stock.screener.controllers;


import com.bullpick.stock.screener.models.Stock;
import com.bullpick.stock.screener.models.StockFinancials;
import com.bullpick.stock.screener.models.StockQuote;
import com.bullpick.stock.screener.models.data.PortfolioRepository;
import com.bullpick.stock.screener.models.data.StockQuoteRepository;
import com.bullpick.stock.screener.models.data.StockRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.bytebuddy.description.method.MethodDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class StockController {

    @Autowired
    private StockQuoteRepository stockQuoteRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    private String apiKey = System.getenv("API_KEY");

    @GetMapping("/stock")
    public String stockDetails(@RequestParam String search, Model model) {

        String[] stockTest = {"https://widget.finnhub.io/widgets/stocks/chart?symbol=", search, "&amp;watermarkColor=%231db954&amp;backgroundColor=%23222222&amp;textColor=white"};
        String candlestickWidget = String.join("", stockTest);

        model.addAttribute("candlestickWidget", candlestickWidget);
        model.addAttribute("search", search);

        //get quotes
        String stockQuote = webClientBuilder
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(2 * 1024 * 1024))
                .build()
                .get()
                .uri("https://finnhub.io/api/v1/quote?symbol=" + search.toUpperCase() + "&token=" + apiKey)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        //convert json to java object
        Gson gsonStockQuote = new Gson();
        StockQuote stockQuoteObject = gsonStockQuote.fromJson(stockQuote, StockQuote.class);
        System.out.println(stockQuoteObject);
        model.addAttribute("stockQuoteObject", stockQuoteObject);

        //get financials
        //financials mapping to classes via gson not currently functional
        //api call is functional
//        String stockFinancials = webClientBuilder
//                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(2 * 1024 * 1024))
//                .build()
//                .get()
//                .uri("https://finnhub.io/api/v1/stock/metric?symbol=" + search.toUpperCase() + "&metric=all&token=" + apiKey)
//                .retrieve()
//                .bodyToMono(String.class)
//                .block();


//        Gson gsonStockFinancials = new Gson();
//        StockFinancials stockFinancialsObject = gsonStockFinancials.fromJson(stockFinancials, StockFinancials.class);
//       //System.out.println(stockFinancials);
//        model.addAttribute("stockFinancialsObject", stockFinancialsObject.getFiftyTwoWeekHigh());
//        model.addAttribute("stockFinancials", stockFinancials);

//        Type stockFinancialsListType = new TypeToken<ArrayList<StockFinancials>>(){}.getType();
//
//        List<StockFinancials> stockFinancialsList = gsonStockFinancials.fromJson(stockFinancials, stockFinancialsListType);
//        model.addAttribute("stockFinancialsList", stockFinancialsList);

//        Type stockFinancialsType = new TypeToken<HashMap<String, StockFinancials>>(){}.getType();
////        StockFinancials stockFinancialsObject = gsonStockFinancials.fromJson(stockFinancials, StockFinancials.class);
//        HashMap<String, StockFinancials> stockFinancialsHashMap = gsonStockFinancials.fromJson(stockFinancials, stockFinancialsType);
//
//        model.addAttribute("stockFinancialsHashMap", stockFinancialsHashMap);
        stockQuoteObject.setSymbol(search);
        model.addAttribute(new StockQuote());
        stockQuoteRepository.save(stockQuoteObject);
        return "stock_details";
    }

    @GetMapping("/stock/add")
    public String displayAddStockForm(Model model) {
        model.addAttribute("portfolios", portfolioRepository.findAll());
        model.addAttribute("stocks", stockRepository.findAll());
        model.addAttribute(new Stock());
        return "add_stock";
    }

    @PostMapping("/stock/add")
    public String processAddStock(@ModelAttribute StockQuote stockQuoteObject, Model model) {
        model.addAttribute(new StockQuote());
        stockQuoteRepository.save(stockQuoteObject);

        return "add_stock";
    }

   @GetMapping("/list")
   //@RequestMapping(value = "nyseStockList")
   //@ResponseBody
    public String stockList(Model model) {
        //get nyse stock information to list
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

       //return nyseStockList;

       model.addAttribute("nyseStockList", nyseStockList);

       //get nasdaq capitol market information
       String nasdaqStockInformation = webClientBuilder
               .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(2 * 1024 * 1024))
               .build()
               .get()
               .uri("https://finnhub.io/api/v1/stock/symbol?exchange=US&mic=XNAS&token=" + apiKey)
               .retrieve()
               .bodyToMono(String.class)
               .block();

       Gson gsonNasdaqStock = new Gson();

       Type nasdaqStockInformationListType = new TypeToken<ArrayList<Stock>>(){}.getType();

       List<Stock> nasdaqStockList = gsonNasdaqStock.fromJson(nasdaqStockInformation, nasdaqStockInformationListType);

       //return nasdaqStockList;

       model.addAttribute("nasdaqStockList", nasdaqStockList);


       return "list_stocks";

   }



}
