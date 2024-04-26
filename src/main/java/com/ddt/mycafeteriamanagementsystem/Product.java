package com.ddt.mycafeteriamanagementsystem;

import java.sql.Date;

public class Product {
    private Integer id;
    private String prod_id;
    private String prod_name;
    private int categories_id;
    private Integer stock;
    private Double price;
    private String status;
    private  String image;
    private Date date;

    public Product() {}

    public Product(Integer id, String prod_id, String prod_name, int categories_id, Integer stock, Double price, String status, String image) {
        this.id = id;
        this.prod_id = prod_id;
        this.prod_name = prod_name;
        this.categories_id = categories_id;
        this.stock = stock;
        this.price = price;
        this.status = status;
        this.image = image;
    }

    public Product(Integer id, String prod_id, String prod_name, int categories_id, Integer stock, Double price, String status, String image, Date date) {
        this.id = id;
        this.prod_id = prod_id;
        this.prod_name = prod_name;
        this.categories_id = categories_id;
        this.stock = stock;
        this.price = price;
        this.status = status;
        this.image = image;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public int getCategories_id() {
        return categories_id;
    }

    public void setCategories_id(int categories_id) {
        this.categories_id = categories_id;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
