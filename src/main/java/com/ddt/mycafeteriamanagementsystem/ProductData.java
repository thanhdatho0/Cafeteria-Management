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

    //Inventory_form..............



    //Product_form
    public ProductData(Integer id, String productId, String productName, String type, Integer stock, Double price, String image){
        this.id = id;
        this.productID = productId;
        this.productName = productName;
        this.type = type;
        this.stock = stock;
        this.price = price;
        this.image = image;
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

    public String getType() {return type;}

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
}

