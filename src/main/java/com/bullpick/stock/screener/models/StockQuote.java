package com.bullpick.stock.screener.models;

public class StockQuote extends Stock {

    private float pc; //previous close price
    private float o; //open price
    private float c; //close price
    private float h; //day high price
    private float l; //day low price
    private int t; //trade volume

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
}
