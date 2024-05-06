package com.ddt.mycafeteriamanagementsystem;

import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ProductDAO extends DAO<Product> {
    ResultSet check(String id) throws SQLException;
    ObservableList<Product> DataList() throws SQLException;
    ObservableList<Product> DataTypeList(int categories_id) throws SQLException;

    ResultSet getIDProduct(Product product) throws SQLException;
    Product getProductByName(String name) throws SQLException;
    Categories getCategories(int id) throws SQLException;

}
