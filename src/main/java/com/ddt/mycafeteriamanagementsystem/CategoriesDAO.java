package com.ddt.mycafeteriamanagementsystem;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface CategoriesDAO extends DAO<Categories>{
    ResultSet getAllCate(Categories categories) throws SQLException;
}
