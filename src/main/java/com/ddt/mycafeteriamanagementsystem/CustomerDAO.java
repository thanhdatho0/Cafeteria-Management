package com.ddt.mycafeteriamanagementsystem;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface CustomerDAO extends DAO<Customer>{
    ResultSet maxCustomer(Customer customer) throws SQLException;

}
