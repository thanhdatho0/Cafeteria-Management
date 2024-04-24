package com.ddt.mycafeteriamanagementsystem;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private int id;
    private String em_username;
    private int customer_id;
    private Date date;
    private List<OrderDetails> items = new ArrayList<OrderDetails>();

    public Order() {}

    public Order(int id, String em_username, int customer_id, Date date) {
        this.id = id;
        this.em_username = em_username;
        this.customer_id = customer_id;
        this.date = date;
    }

    public Order(int id, String em_username, int customer_id) {
        this.id = id;
        this.em_username = em_username;
        this.customer_id = customer_id;
    }

    public Order(int id, String em_username, int customer_id, Date date, List<OrderDetails> items) {
        this.id = id;
        this.em_username = em_username;
        this.customer_id = customer_id;
        this.date = date;
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEm_username() {
        return em_username;
    }

    public void setEm_username(String em_username) {
        this.em_username = em_username;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<OrderDetails> getItems() {
        return items;
    }

    public void setItems(List<OrderDetails> items) {
        this.items = items;
    }

//    public void addItems(ProductData prod_id, int quantity, double price){
//        this.items.add(new OrderDetails(id, prod_id, quantity, price));
//    }
}
