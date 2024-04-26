package com.ddt.mycafeteriamanagementsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class OrderDetailsDAOImpl implements OrderDetailsDAO{
    private Connection connect;
    private PreparedStatement prepare;
    ResultSet result;

    @Override
    public OrderDetails get(int id) throws SQLException {
        return null;
    }

    @Override
    public List<OrderDetails> getAll() throws SQLException {
        return null;
    }

    @Override
    public int save(OrderDetails orderDetails) throws SQLException {
        return 0;
    }

    @Override
    public void insert(OrderDetails orderDetails) throws SQLException {
        connect = Database.connectDB();

        String insertPay = "INSERT INTO `order details` (order_id, prod_id, quantity) "
                + "VALUES(?,?,?)";
        prepare = connect.prepareStatement(insertPay);

        prepare.setString(1, String.valueOf(orderDetails.getOrder_id()));
        prepare.setString(2, String.valueOf(orderDetails.getProd_id()));
        prepare.setString(3, String.valueOf(orderDetails.getQuantity()));

        prepare.executeUpdate();
    }

    @Override
    public void update(OrderDetails orderDetails) throws SQLException {

    }

    @Override
    public void delete(OrderDetails orderDetails) throws SQLException {

    }
}
