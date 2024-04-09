package com.ddt.mycafeteriamanagementsystem;

import java.sql.Date;

public class ProductData {
    private Integer id;
    private String productID;
    private String productName;
    private String type;
    private Integer stock;
    private Double price;
    private String status;
    private  String image;
    private Date date;

    //Inventory_form
    public ProductData(Integer id, String productID, String productName, String type, Integer stock, Double price, String status, String image, Date date) {
        this.id = id;
        this.productID = productID;
        this.productName = productName;
        this.type = type;
        this.stock = stock;
        this.price = price;
        this.status = status;
        this.image = image;
        this.date = date;
    }

    //Product_form
    public ProductData(Integer id, String productId, String productName, Integer stock, Double price, String status, String image, Date date){
        this.id = id;
        this.productID = productId;
        this.productName = productName;
        this.stock = stock;
        this.price = price;
        this.status = status;
        this.image = image;
        this.date = date;
    }

    //Order_form
    public ProductData(Integer id, String productId, String productName, Double price, String status, String image, Date date){
        this.id = id;
        this.productID = productId;
        this.productName = productName;
        this.price = price;
        this.status = status;
        this.image = image;
        this.date = date;
    }


    public Integer getId() {
        return id;
    }

    public String getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public String getType() {
        return type;
    }

    public Integer getStock() {
        return stock;
    }

    public Double getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    public String getImage() {
        return image;
    }

    public Date getDate() {
        return date;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

