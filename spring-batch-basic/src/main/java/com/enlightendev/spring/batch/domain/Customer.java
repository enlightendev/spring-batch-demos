package com.enlightendev.spring.batch.domain;

/**
 * Created by JL25292 on 4/29/2015.
 */
public class Customer {

    private String customerName;
    private int qty;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
