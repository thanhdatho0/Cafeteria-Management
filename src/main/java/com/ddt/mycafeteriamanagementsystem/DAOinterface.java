package com.ddt.mycafeteriamanagementsystem;

public interface DAOinterface<T> {
    public void insert(T t);
    public void update(T t);
    public  int delete(T t);
}
