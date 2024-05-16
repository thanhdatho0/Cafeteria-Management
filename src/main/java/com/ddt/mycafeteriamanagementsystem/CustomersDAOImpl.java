package com.ddt.mycafeteriamanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomersDAOImpl implements CustomersDAO{
    @Override
    public Customers get(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Customers> getAll() throws SQLException {
        return null;
    }

    @Override
    public int save(Customers customers) throws SQLException {
        return 0;
    }

    @Override
    public void insert(Customers customers) throws SQLException {
        Connection connect = Database.connectDB();
        String insertStatement = "INSERT INTO customers (order_id) VALUES (?)";

        PreparedStatement prepare = connect.prepareStatement(insertStatement);
        prepare.setInt(1, customers.getOrder().getId());
        prepare.executeUpdate();
    }

    @Override
    public void update(Customers customers) throws SQLException {

    }

    @Override
    public void delete(Customers customers) throws SQLException {

    }


    @Override
    public ObservableList<CustomerDetails> customerDetailsDataList() throws SQLException {
        CustomerDetails customerDetails;
        ObservableList<CustomerDetails> customerDetailsList = FXCollections.observableArrayList();
        Connection connect = Database.connectDB();
        String sql = "SELECT c.id, sum(od.quantity*p.price) as total, o.date " +
                "FROM customers c " +
                "INNER JOIN `order` o ON o.id = c.order_id " +
                "INNER JOIN `order details` od ON od.order_id = o.id " +
                "INNER JOIN product p ON p.id = od.prod_id " +
                "GROUP BY c.id;";
        PreparedStatement prepare = connect.prepareStatement(sql);
        ResultSet result = prepare.executeQuery();
        while (result.next()){
            customerDetails = new CustomerDetails(
                    result.getInt(1),
                    result.getDouble(2),
                    result.getDate(3),
                    Data.username
            );
            customerDetailsList.add(customerDetails);
        }
        return customerDetailsList;
    }
}
