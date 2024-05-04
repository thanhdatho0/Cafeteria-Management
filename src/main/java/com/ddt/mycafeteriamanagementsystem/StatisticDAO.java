package com.ddt.mycafeteriamanagementsystem;

import java.sql.SQLException;

public interface StatisticDAO extends DAO<Statistic> {
    int getNumberOfCustomer() throws SQLException;

    double getDayIncome() throws SQLException;

    double getMonthIncome() throws SQLException;

    int getSoldNumber() throws SQLException;
}





