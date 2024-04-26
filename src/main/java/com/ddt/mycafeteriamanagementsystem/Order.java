package com.ddt.mycafeteriamanagementsystem;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private int id;
    private int employee_id;
    private Date date;
    private List<OrderDetails> items = new ArrayList<OrderDetails>();

    public Order() {}

    public Order(int id, int employee_id) {
        this.id = id;
        this.employee_id = employee_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
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
