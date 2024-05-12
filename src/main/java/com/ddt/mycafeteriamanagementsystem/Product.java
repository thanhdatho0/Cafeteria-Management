package com.ddt.mycafeteriamanagementsystem;

import java.sql.Date;

public class Product {
    private int id;
    private String prod_id;
    private String prod_name;
    private Categories categories;
    private int stock;
    private double price;
    private int quantity;
    private String status;
    private  String image;
    private Date date;
    private double pr;
    private int available;


    //public Product() {}

    public Product(int id, String prod_id, String prod_name, Categories categories, int stock, double price, String status, String image, Date date) {
        this.id = id;
        this.prod_id = prod_id;
        this.prod_name = prod_name;
        this.categories = categories;
        this.stock = stock;
        this.price = price;
        this.status = status;
        this.image = image;
        this.date = date;
    }

    public Product(String prod_id, String prod_name, Categories categories, int stock, double price, String status, String image, Date date) {
        this.prod_id = prod_id;
        this.prod_name = prod_name;
        this.categories = categories;
        this.stock = stock;
        this.price = price;
        this.status = status;
        this.image = image;
        this.date = date;
    }

    //Order_form
    public Product(int id, String productId, String prod_name, int quantity, double price, String image, Date date){
        this.id = id;
        this.prod_id = productId;
        this.prod_name = prod_name;
        this.quantity = quantity;
        this.price = price;
        this.image = image;
        this.date = date;
    }

    // delete_form
    public Product(Product product) {
        this.prod_id = product.prod_id;
        this.prod_name = product.prod_name;
        this.categories = product.categories;
        this.stock = product.stock;
        this.price = product.price;
        this.status = "Unavailable";
        this.image = product.image;
        this.date = product.date;
        this.available = 0;
    }

    public Product() {this(0, null, null, null, 0, 0, null, null, null);}

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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
    public double getPr() {
        return pr;
    }
    public void setPr(double pr) {
        this.pr = pr;
    }
    public int getQuantity() {
        return quantity;
    }
}
