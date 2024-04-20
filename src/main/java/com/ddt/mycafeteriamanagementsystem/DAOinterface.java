package com.ddt.mycafeteriamanagementsystem;

import java.sql.SQLException;

public interface DAOinterface<T> {
    public void insert(T t);
    public void update(T t);
    public  int delete(T t);
}
