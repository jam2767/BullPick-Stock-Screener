package com.bullpick.stock.screener.models.dto;

import com.bullpick.stock.screener.models.Portfolio;
import com.bullpick.stock.screener.models.Stock;
import com.sun.istack.NotNull;
import org.hibernate.annotations.NotFound;

public class PortfolioStockDTO {

    @NotNull
    private Portfolio portfolio;

    @NotNull
    private Stock stock;

    public PortfolioStockDTO() {}

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
}
