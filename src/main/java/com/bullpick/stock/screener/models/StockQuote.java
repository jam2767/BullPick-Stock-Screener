package com.bullpick.stock.screener.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stockQuotes")
public class StockQuote extends Stock {

//    @Id
//    @GeneratedValue()
//    private int id;

    private float pc; //previous close price
    private float o; //open price
    private float c; //current price
    private float h; //day high price
    private float l; //day low price
    private int t; //trade volume
    private float priceWhenAdded; //current price when added to a portfolio

    public StockQuote(float previousClose, float openPrice, float currentPrice,
                      float dayHi, float dayLo, int tradeVol, float priceOnAdded) {
        this.pc = previousClose;
        this.o = openPrice;
        this.c = currentPrice;
        this.h = dayHi;
        this.l = dayLo;
        this.t = tradeVol;
        this.priceWhenAdded = priceOnAdded;
    }

    public StockQuote() {}

//    public int getId() {
//        return id;
//    }

    public float getPc() {
        return pc;
    }

    public void setPc(float pc) {
        this.pc = pc;
    }

    public float getO() {
        return o;
    }

    public void setO(float o) {
        this.o = o;
    }

    public float getC() {
        return c;
    }

    public void setC(float c) {
        this.c = c;
    }

    public float getH() {
        return h;
    }

    public void setH(float h) {
        this.h = h;
    }

    public float getL() {
        return l;
    }

    public void setL(float l) {
        this.l = l;
    }

    public int getT() {
        return t;
    }

    public void setT(int t) {
        this.t = t;
    }

    public float getPriceWhenAdded() {
        return priceWhenAdded;
    }

    //can only set price when added once
    public void setPriceWhenAdded(float priceWhenAdded) {
        this.priceWhenAdded = priceWhenAdded;
    }
}
