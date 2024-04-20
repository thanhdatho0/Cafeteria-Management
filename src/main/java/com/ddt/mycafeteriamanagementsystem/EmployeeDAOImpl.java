package com.ddt.mycafeteriamanagementsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class EmployeeDAOImpl implements EmployeeDAO{
    @Override
    public Employee get(int id) throws SQLException {
        Connection con = Database.connectDB();
        Employee employee = null;
        String sql = "SELECT * FROM employee WHERE id = ?";
        PreparedStatement prepare = con.prepareStatement(sql);
        prepare.setInt(1, id);

        ResultSet res = prepare.executeQuery();

        if(res.next()){
            int EmplId = res.getInt("id");
            String username = res.getString("username");
            String password = res.getString("password");
            String question = res.getString("question");
            String answer = res.getString("answer");
            java.sql.Date date = res.getDate("date");

            employee = new Employee(id, username, password, question, answer, date);
        }
        return employee;
    }

    @Override
    public List<Employee> getAll() throws SQLException {
        List<Employee> employeeList = new ArrayList<>();
        int fId = 1;
        Employee employee = get(fId);
        while(employee != null){
            employeeList.add(employee);
            fId++;
            employee = get(fId);
        }
        return employeeList;
    }

    @Override
    public int save(Employee employee) throws SQLException {
        return 0;
    }

    @Override
    public int insert(Employee employee) throws SQLException {
        Connection con = Database.connectDB();
        String sql = "INSERT INTO employee(username, password, question, answer, date)" + "VALUES(?, ?, ?, ?, ?)";

        PreparedStatement prepare = con.prepareStatement(sql);

        prepare.setString(1, employee.getUsername());
        prepare.setString(2, employee.getPassword());
        prepare.setString(3, employee.getQuestion());
        prepare.setString(4, employee.getAnswer());

        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        prepare.setString(5, String.valueOf(sqlDate));

        return prepare.executeUpdate();
    }

    @Override
    public int update(Employee employee) throws SQLException {
        Connection con = Database.connectDB();
        String sql = "UPDATE employee SET username = ?, password = ?, question = ?, answer = ?, date = ? WHERE id = ?";

        PreparedStatement prepare  = con.prepareStatement(sql);

        prepare.setString(1, employee.getUsername());
        prepare.setString(2, employee.getPassword());
        prepare.setString(3, employee.getQuestion());
        prepare.setString(4, employee.getAnswer());

        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        prepare.setString(5, String.valueOf(sqlDate));
        prepare.setInt(6, employee.getId());

        return prepare.executeUpdate();
    }

    @Override
    public int delete(Employee employee) throws SQLException {
        Connection con = Database.connectDB();
        String sql = "DELETE FROM employee WHERE id = ?";

        PreparedStatement prepare = con.prepareStatement(sql);

        prepare.setInt(1, employee.getId());

        return prepare.executeUpdate();
    }
}
