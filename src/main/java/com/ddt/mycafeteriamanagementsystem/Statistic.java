package com.ddt.mycafeteriamanagementsystem;

public class Statistic {
    Customers customer;

    public Statistic(Customers customer) {
        this.customer = customer;
    }

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }
}
