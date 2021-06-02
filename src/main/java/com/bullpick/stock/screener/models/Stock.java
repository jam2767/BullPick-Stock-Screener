package com.bullpick.stock.screener.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "stockDescription")
public class Stock {

    @Id
    @GeneratedValue()
    private int id;

//    @ManyToMany(mappedBy = "stocks")
//    private final List<Portfolio> portfolios = new ArrayList<>();

    private String description;
   private String symbol;
   private String type;
   private String mic;

//    public List<Portfolio> getPortfolios() {
//        return portfolios;
//    }

    public int getId() {
        return id;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMic() {
        return mic;
    }

    public void setMic(String mic) {
        this.mic = mic;
    }
}
