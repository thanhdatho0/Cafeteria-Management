package com.ddt.mycafeteriamanagementsystem;

import java.util.Date;

public class CustomerDetails {
    private int customer_id;
    private double total;
    private Date date;
    private String cashier;

    public CustomerDetails() {
    }

    public CustomerDetails(int customer_id, double total, Date date, String cashier) {
        this.customer_id = customer_id;
        this.total = total;
        this.date = date;
        this.cashier = cashier;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCashier() {
        return cashier;
    }

    public void setCashier(String cashier) {
        this.cashier = cashier;
    }

}
