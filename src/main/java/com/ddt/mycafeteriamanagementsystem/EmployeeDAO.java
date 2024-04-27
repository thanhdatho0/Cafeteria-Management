package com.ddt.mycafeteriamanagementsystem;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface EmployeeDAO extends DAO<Employee>{
    ResultSet login(Employee employee) throws SQLException;

    ResultSet isUserExist(Employee employee) throws SQLException;

    ResultSet isTrueInfo(Employee employee) throws SQLException;

    void updatePass(Employee employee) throws SQLException;

    ResultSet getIDEmployee(Employee employee) throws SQLException;

}
