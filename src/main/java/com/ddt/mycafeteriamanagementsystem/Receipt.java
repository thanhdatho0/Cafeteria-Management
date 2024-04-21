package com.ddt.mycafeteriamanagementsystem;

public class Receipt {
    private int id;
    private int customer_id;
    private double total;
    private String em_username;

    public Receipt() {}

    public Receipt(int id, int customer_id, double total, String em_username) {
        this.id = id;
        this.customer_id = customer_id;
        this.total = total;
        this.em_username = em_username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEm_username() {
        return em_username;
    }

    public void setEm_username(String em_username) {
        this.em_username = em_username;
    }
}
