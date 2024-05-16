package com.ddt.mycafeteriamanagementsystem;

public class Customers {
    private int id;
    private Order order;
    private double total;

    public Customers(){}

    public Customers(int id, Order order, double total) {
        this.id = id;
        this.order = order;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public double getTotal() {
        total = order.total_amount();
        return total;
    }

    public void setTotal(int total){ this.total = total; }

    @Override
    public String toString() {
        return String.valueOf(order.getId());
    }

}
