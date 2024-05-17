package com.ddt.mycafeteriamanagementsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.text.DecimalFormat;

public class StatisticDAOImpl implements StatisticDAO{
    @Override
    public Statistic get(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Statistic> getAll() throws SQLException {
        return null;
    }

    @Override
    public int save(Statistic statistic) throws SQLException {
        return 0;
    }

    @Override
    public void insert(Statistic statistic) throws SQLException {

    }

    @Override
    public void update(Statistic statistic) throws SQLException {

    }

    @Override
    public void delete(Statistic statistic) throws SQLException {

    }

    @Override
    public int getNumberOfCustomer() throws SQLException {
        Connection connect = Database.connectDB();
        int count = 0;
        String sql = "SELECT id FROM customers";
        PreparedStatement prepare = connect.prepareStatement(sql);
        ResultSet result = prepare.executeQuery();
        while (result.next())
            count++;
        return count;
    }

    @Override
    public double getDayIncome() throws SQLException {
        double dayIncome = 0;
        Connection connect = Database.connectDB();
        String sql = "SELECT SUM(p.price*od.quantity) " +
                "FROM `order details` od " +
                "INNER JOIN product p ON p.id = od.prod_id " +
                "INNER JOIN `order` o ON o.id = od.order_id " +
                "WHERE o.date = ?";
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        PreparedStatement prepare = connect.prepareStatement(sql);
        prepare.setString(1, String.valueOf(sqlDate));
        ResultSet result = prepare.executeQuery();
        if (result.next()){
            dayIncome = result.getDouble(1);
        }
        DecimalFormat format = new DecimalFormat("##.00");
        return Double.parseDouble(format.format(dayIncome));
    }

    @Override
    public double getMonthIncome() throws SQLException {
        double monthIncome = 0;

        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sqlDate);
        int month = calendar.get(Calendar.MONTH) + 1;

        Connection connect = Database.connectDB();
        String sql = "SELECT SUM(p.price*od.quantity) " +
                "FROM `order details` od " +
                "INNER JOIN product p ON p.id = od.prod_id " +
                "INNER JOIN `order` o ON o.id = od.order_id " +
                "WHERE MONTH(o.date) = ?";
        PreparedStatement prepare = connect.prepareStatement(sql);
        prepare.setInt(1, month);
        ResultSet result = prepare.executeQuery();
        while (result.next()){
            monthIncome += result.getDouble(1);
        }
        DecimalFormat format = new DecimalFormat("##.00");
        return Double.parseDouble(format.format(monthIncome));
    }

    @Override
    public int getSoldNumber() throws SQLException {
        int solds = 0;
        Connection connect = Database.connectDB();
        String sql = "SELECT SUM(od.quantity) FROM `order details` od " +
                "INNER JOIN `order` o ON o.id = od.order_id " +
                "WHERE o.date = ?";
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        PreparedStatement prepare = connect.prepareStatement(sql);
        prepare.setString(1, String.valueOf(sqlDate));
        ResultSet result = prepare.executeQuery();
        while (result.next()){
            solds += result.getDouble(1);
        }
        return solds;
    }

    @Override
    public ResultSet dayCustomersStatistic() throws SQLException {
        Connection connect = Database.connectDB();
        String sql = "SELECT o.date, count(c.id) FROM customers c " +
                "INNER JOIN `order` o ON o.id = c.order_id " +
                "GROUP BY date ORDER BY TIMESTAMP(date) ASC";
        PreparedStatement prepare = connect.prepareStatement(sql);
        return prepare.executeQuery();
    }

    @Override
    public ResultSet dayIncomesStatistic() throws SQLException {
        String sql = "SELECT o.date ,SUM(p.price*od.quantity) " +
                "FROM `order details` od " +
                "INNER JOIN product p ON p.id = od.prod_id " +
                "INNER JOIN `order` o ON o.id = od.order_id " +
                "GROUP BY o.date ORDER BY TIMESTAMP(o.date) ASC";
        Connection connect = Database.connectDB();
        PreparedStatement prepare = connect.prepareStatement(sql);
        return prepare.executeQuery();
    }
}
