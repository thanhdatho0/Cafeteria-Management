package com.ddt.mycafeteriamanagementsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class CusotmerDAOImpl implements CustomerDAO{

    @Override
    public Customer get(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Customer> getAll() throws SQLException {
        return null;
    }

    @Override
    public int save(Customer customer) throws SQLException {
        return 0;
    }

    @Override
    public void insert(Customer customer) throws SQLException {
        Connection connect = Database.connectDB();

        String insertData  = "INSERT INTO customer"
                + "(customer_id, prod_id, prod_name, quantity, price, date, em_username, image)"
                + "VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement prepare = connect.prepareStatement(insertData );

        prepare.setString(1, String.valueOf(customer.getCustomer_id()));
        prepare.setString(2, customer.getProd_id());
        prepare.setString(3, customer.getProd_name());
        prepare.setString(4, String.valueOf(customer.getQuantity()));
        prepare.setString(5, String.valueOf(customer.getPrice()));
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        prepare.setString(6, String.valueOf(sqlDate));
        prepare.setString(7, customer.getEm_username());
        prepare.setString(8, customer.getImage());

        prepare.executeUpdate();
    }

    @Override
    public void update(Customer customer) throws SQLException {

    }

    @Override
    public void delete(Customer customer) throws SQLException {

    }


    @Override
    public ResultSet getAllCustomer(Customer customer) throws SQLException {
        Connection connect = Database.connectDB();
        String sql = "SELECT * FROM customer WHERE customer_id = ?";
        PreparedStatement prepare = connect.prepareStatement(sql);
        prepare.setString(1, String.valueOf(customer.getCustomer_id()));
        ResultSet result = prepare.executeQuery();
        return result;
    }
}
