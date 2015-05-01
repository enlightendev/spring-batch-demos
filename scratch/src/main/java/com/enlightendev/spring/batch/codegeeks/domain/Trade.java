package com.enlightendev.spring.batch.codegeeks.domain;

/**
 */
public class Trade {

    private String ticker;

    private double shares;

    public Trade(){}

    public Trade(String ticker, double shares){
        this.ticker = ticker;
        this.shares = shares;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public double getShares() {
        return shares;
    }

    public void setShares(double shares) {
        this.shares = shares;
    }
}
