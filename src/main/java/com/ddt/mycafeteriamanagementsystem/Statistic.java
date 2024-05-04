package com.ddt.mycafeteriamanagementsystem;

public class Statistic {
    Customer customer;

    public Statistic(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
