package com.ddt.mycafeteriamanagementsystem;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ProductCardDAO extends DAO<Product>{
    ResultSet getIDProduct(Product product) throws SQLException;
}
