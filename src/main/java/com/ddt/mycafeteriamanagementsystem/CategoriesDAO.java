package com.ddt.mycafeteriamanagementsystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface CategoriesDAO extends DAO<Categories>{
    ResultSet getAllCate(Categories categories) throws SQLException;

    List<Product> getAllProduct(Categories categories) throws SQLException;
}
