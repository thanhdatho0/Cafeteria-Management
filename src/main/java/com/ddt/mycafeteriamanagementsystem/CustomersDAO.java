package com.ddt.mycafeteriamanagementsystem;

import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public interface CustomersDAO extends DAO<Customers>{
    ObservableList<CustomerDetails> customerDetailsDataList() throws SQLException;
}

