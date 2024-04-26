package com.ddt.mycafeteriamanagementsystem;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface OrderDAO extends DAO<Order>{
    ResultSet getAllOrder(Order order) throws SQLException;
}
