package com.ddt.mycafeteriamanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class ReceiptDAOImpl implements ReceiptDAO{
    private Connection connect;
    private PreparedStatement prepare;
    ResultSet result;

    public static ReceiptDAOImpl getInstance()
    {
        return new ReceiptDAOImpl();
    }

    @Override
    public Receipt get(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Receipt> getAll() throws SQLException {
        return null;
    }

    @Override
    public int save(Receipt receipt) throws SQLException {
        return 0;
    }

    @Override
    public void insert(Receipt receipt) throws SQLException {
        connect = Database.connectDB();

        String insertPay = "INSERT INTO receipt (customer_id, total, date, em_username) "
                + "VALUES(?,?,?,?)";
        prepare = connect.prepareStatement(insertPay);

        prepare.setString(1, String.valueOf(receipt.getCustomer_id()));
        prepare.setString(2, String.valueOf(receipt.getTotal()));

        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        prepare.setString(3, String.valueOf(sqlDate));
        prepare.setString(4, receipt.getEm_username());

        prepare.executeUpdate();
    }

    @Override
    public void update(Receipt receipt) throws SQLException {

    }

    @Override
    public void delete(Receipt receipt) throws SQLException {

    }

    @Override
    public ObservableList<CustomerData> DataList() throws SQLException {
        ObservableList<CustomerData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM receipt";

        Connection connect = Database.connectDB();
        PreparedStatement prepare = connect.prepareStatement(sql);
        ResultSet result = prepare.executeQuery();

        CustomerData cData;

        while (result.next()){
            cData = new CustomerData(
                    result.getInt("id"),
                    result.getInt("customer_id"),
                    result.getDouble("total"),
                    result.getDate("date"),
                    result.getString("em_username"));

            listData.add(cData);
        }
        return listData;
    }
}
