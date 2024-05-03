package com.ddt.mycafeteriamanagementsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StatisticDAOImpl implements StatisticDAO{
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
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
        connect = Database.connectDB();
        int count = 0;
        String sql = "SELECT id FROM customer";
        prepare = connect.prepareStatement(sql);
        result = prepare.executeQuery();
        while (result.next())
            count++;
        return count;
    }
}
