package com.ddt.mycafeteriamanagementsystem;

import java.sql.Date;

public class Customer {
    private int id;
    private int customer_id;
    private String prod_id;
    private String prod_name;
    private String type;
    private int quantity;
    private double price;
    private Date date;
    private String em_username;
    private String image;

    public Customer() {}

    public Customer(int id, int customer_id, String prod_id, String prod_name, String type, int quantity, double price, String em_username, String image) {
        this.id = id;
        this.customer_id = customer_id;
        this.prod_id = prod_id;
        this.prod_name = prod_name;
        this.type = type;
        this.quantity = quantity;
        this.price = price;
        this.em_username = em_username;
        this.image = image;
    }

    public Customer(int id, int customer_id, String prod_id, String prod_name, String type, int quantity, double price, Date date, String em_username, String image) {
        this.id = id;
        this.customer_id = customer_id;
        this.prod_id = prod_id;
        this.prod_name = prod_name;
        this.type = type;
        this.quantity = quantity;
        this.price = price;
        this.date = date;
        this.em_username = em_username;
        this.image = image;
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

    public String getProd_id() {
        return prod_id;
    }

    public void setProd_id(String prod_id) {
        this.prod_id = prod_id;
    }

    public String getProd_name() {
        return prod_name;
    }

    public void setProd_name(String prod_name) {
        this.prod_name = prod_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEm_username() {
        return em_username;
    }

    public void setEm_username(String em_username) {
        this.em_username = em_username;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", customer_id=" + customer_id +
                ", prod_id='" + prod_id + '\'' +
                ", prod_name='" + prod_name + '\'' +
                ", type='" + type + '\'' +
                ", quantity=" + quantity +
                ", date=" + date +
                ", em_username='" + em_username + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
