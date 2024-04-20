package com.ddt.mycafeteriamanagementsystem;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestDao {
    public static void main(String[] args) throws SQLException {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        Employee employee = new Employee(1, "thanhdat22", "thanhdat22", "How many ex did you have?", "2");
        if(employeeDAO.update(employee) == 1){
            System.out.println("Success");
        }

    }
}
