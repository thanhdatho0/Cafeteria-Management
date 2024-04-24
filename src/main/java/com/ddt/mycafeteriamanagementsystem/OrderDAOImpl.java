package com.ddt.mycafeteriamanagementsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class OrderDAOImpl implements OrderDAO{
    private Connection connect;
    private PreparedStatement prepare;
    ResultSet result;

    @Override
    public Order get(int id) throws SQLException {
        connect = Database.connectDB();

        return null;
    }

    @Override
    public List<Order> getAll() throws SQLException {
        return null;
    }

    @Override
    public int save(Order order) throws SQLException {
        return 0;
    }

    @Override
    public void insert(Order order) throws SQLException {
        connect = Database.connectDB();

        String sql = "INSERT INTO `order` (em_username, customer_id, date) "
                + "VALUES(?,?,?)";
        prepare = connect.prepareStatement(sql);

        prepare.setString(1, order.getEm_username());
        prepare.setString(2, String.valueOf(order.getCustomer_id()));
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        prepare.setString(3, String.valueOf(sqlDate));

        prepare.executeUpdate();
    }

    @Override
    public void update(Order order) throws SQLException {

    }

    @Override
    public void delete(Order order) throws SQLException {

    }
}
