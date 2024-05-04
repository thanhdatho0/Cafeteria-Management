package com.ddt.mycafeteriamanagementsystem;

import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ProductCardDAO extends DAO<Product>{
    ResultSet getIDProduct(Product product) throws SQLException;

    Product getProductByName(String name) throws SQLException;

    Categories getCategories(int id) throws SQLException;
}




