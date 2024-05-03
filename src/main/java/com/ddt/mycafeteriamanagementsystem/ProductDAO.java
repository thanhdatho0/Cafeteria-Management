package com.ddt.mycafeteriamanagementsystem;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ProductDAO extends DAO<Product> {
    ResultSet check(String id) throws SQLException;
}
