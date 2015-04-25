package com.enlightendev.spring.batch.domain;

/**
 * Created by Juan on 4/25/15.
 */
public class Stock {

    private String name;

    private double price;

    public Stock(){

    }

    public Stock(String name, double price){
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
