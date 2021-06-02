package com.bullpick.stock.screener.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "portfolios")
public class Portfolio {

    @Id
    @GeneratedValue()
    private int id;

    private String name;
    private String description;

//    @ManyToMany
//    private final List<Stock> stocks = new ArrayList<>();

//    public List<Stock> getStocks() {
//        return stocks;
//    }

    public int getId() {
        return id;
    }

//    public void setId(int id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
