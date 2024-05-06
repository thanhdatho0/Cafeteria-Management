package com.ddt.mycafeteriamanagementsystem;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private int id;
    private Employee employee;
    private Date date;
    private List<OrderDetails> items = new ArrayList<OrderDetails>();

    public Order() {}

    public Order(int id, Employee employee, Date date, List<OrderDetails> items) {
        this.id = id;
        this.employee = employee;
        this.date = date;
        this.items = items;
    }

    public Order(int id, Employee employee, Date date) {
        this.id = id;
        this.employee = employee;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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
}
