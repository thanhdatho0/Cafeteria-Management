package com.ddt.mycafeteriamanagementsystem;

import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface ReceiptDAO extends DAO<Receipt>{
    ObservableList<CustomerData> DataList() throws SQLException ;
}
