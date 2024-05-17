package com.ddt.mycafeteriamanagementsystem;

import java.sql.SQLException;

public interface OrderDetailsDAO extends DAO<OrderDetails>{
    int getOrderDetailId() throws SQLException;

}
