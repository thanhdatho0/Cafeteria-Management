package com.ddt.mycafeteriamanagementsystem;

import java.util.ArrayList;
import java.util.List;

public class OrderDetails {
    private int id;
    private int prod_id;
    private int quantity;
    private Order order;
    private List<ProductData> productList = new ArrayList<ProductData>();

    public OrderDetails() {}

    public OrderDetails(int id, int prod_id, int quantity) {
        this.id = id;
        this.prod_id = prod_id;
        this.quantity = quantity;
    }

    public OrderDetails(int id, int prod_id, int quantity, Order order) {
        this.id = id;
        this.prod_id = prod_id;
        this.quantity = quantity;
        this.order = order;
    }

    public OrderDetails(int id, int prod_id, int quantity, Order order, List<ProductData> productList) {
        this.id = id;
        this.prod_id = prod_id;
        this.quantity = quantity;
        this.order = order;
        this.productList = productList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProd_id() {
        return prod_id;
    }

    public void setProd_id(int prod_id) {
        this.prod_id = prod_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<ProductData> getProductData() {
        return productList;
    }

    public void setProductData(List<ProductData> productList) {
        this.productList = productList;
    }

//    public void addProduct(ProductData productList, int quantity){
//        this.productList.add(new ProductData(productList, quantity));
//    }
}
