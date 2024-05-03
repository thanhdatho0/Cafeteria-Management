package com.ddt.mycafeteriamanagementsystem;

public class Statistic {
    private int number_of_customer;
    private int product_sold;
    private double[] days_income;

    public Statistic(int number_of_customer, int product_sold, double[] days_income) {
        this.number_of_customer = number_of_customer;
        this.product_sold = product_sold;
        this.days_income = days_income;
    }

    public int getNumber_of_customer() {
        return number_of_customer;
    }

    public void setNumber_of_customer(int number_of_customer) {
        this.number_of_customer = number_of_customer;
    }

    public int getProduct_sold() {
        return product_sold;
    }

    public void setProduct_sold(int product_sold) {
        this.product_sold = product_sold;
    }

    public double[] getDays_income() {
        return days_income;
    }

    public void setDays_income(double[] days_income) {
        this.days_income = days_income;
    }
}
