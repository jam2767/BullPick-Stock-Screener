package com.bullpick.stock.screener.models;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class StockFinancials extends Stock {

    public StockFinancials(float fiftyTwoWeekHigh, String fiftyTwoWeekHighDate, float fiftyTwoWeekLow, String fiftyTwoWeekLowDate) {
        this.fiftyTwoWeekHigh = fiftyTwoWeekHigh;
        this.fiftyTwoWeekHighDate = fiftyTwoWeekHighDate;
        this.fiftyTwoWeekLow = fiftyTwoWeekLow;
        this.fiftyTwoWeekLowDate = fiftyTwoWeekLowDate;
    }

    @SerializedName(value = "52WeekHigh")
    private float fiftyTwoWeekHigh;

    @SerializedName(value = "52WeekHighDate")
    private String fiftyTwoWeekHighDate;

    @SerializedName(value = "52WeekLow")
    private float fiftyTwoWeekLow;

    @SerializedName(value = "52WeekLowDate")
    private String fiftyTwoWeekLowDate;


    public float getFiftyTwoWeekHigh() {
        return fiftyTwoWeekHigh;
    }

    public void setFiftyTwoWeekHigh(float fiftyTwoWeekHigh) {
        this.fiftyTwoWeekHigh = fiftyTwoWeekHigh;
    }

    public String getFiftyTwoWeekHighDate() {
        return fiftyTwoWeekHighDate;
    }

    public void setFiftyTwoWeekHighDate(String fiftyTwoWeekHighDate) {
        this.fiftyTwoWeekHighDate = fiftyTwoWeekHighDate;
    }

    public float getFiftyTwoWeekLow() {
        return fiftyTwoWeekLow;
    }

    public void setFiftyTwoWeekLow(float fiftyTwoWeekLow) {
        this.fiftyTwoWeekLow = fiftyTwoWeekLow;
    }

    public String getFiftyTwoWeekLowDate() {
        return fiftyTwoWeekLowDate;
    }

    public void setFiftyTwoWeekLowDate(String fiftyTwoWeekLowDate) {
        this.fiftyTwoWeekLowDate = fiftyTwoWeekLowDate;
    }


    //    private HashMap<String, String> financials;

}
