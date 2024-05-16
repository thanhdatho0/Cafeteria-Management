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
        Connection connect = Database.connectDB();
        Employee employee = null;
        String sql = "SELECT * FROM employee WHERE id = ?";
        PreparedStatement prepare = connect.prepareStatement(sql);
        prepare.setInt(1, id);

        ResultSet res = prepare.executeQuery();

        if(res.next()){
            int EmplId = res.getInt("id");
            String username = res.getString("username");
            String password = res.getString("password");
            String question = res.getString("question");
            String answer = res.getString("answer");
            java.sql.Date date = res.getDate("date");

            employee = new Employee(EmplId, username, password, question, answer, date);
        }
        return employee;
    }

    @Override
    public List<Employee> getAll() throws SQLException {
        List<Employee> employeeList = new ArrayList<>();
        Connection connect = Database.connectDB();
        String sql = "SELECT * FROM employee";
        PreparedStatement prepare = connect.prepareStatement(sql);
        ResultSet result = prepare.executeQuery();

        if(result.next()){

            Employee employee = new Employee(
                    result.getInt("id"),
                    result.getString("username"),
                    result.getString("password"),
                    result.getString("question"),
                    result.getString("answer"),
                    result.getDate("date")
            );
            employeeList.add(employee);
        }
        return employeeList;
    }

    @Override
    public int save(Employee employee) throws SQLException {
        return 0;
    }

    @Override
    public void insert(Employee employee) throws SQLException {
        Connection connect = Database.connectDB();
        String sql = "INSERT INTO employee(username, password, question, answer, date)" + "VALUES(?, ?, ?, ?, ?)";
         PreparedStatement prepare = connect.prepareStatement(sql);

        prepare.setString(1, employee.getUsername());
        prepare.setString(2, employee.getPassword());
        prepare.setString(3, employee.getQuestion());
        prepare.setString(4, employee.getAnswer());

        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        prepare.setString(5, String.valueOf(sqlDate));

        prepare.executeUpdate();
    }

    @Override
    public void update(Employee employee) throws SQLException {
        Connection connect = Database.connectDB();
        String sql = "UPDATE employee SET username = ?, password = ?, question = ?, answer = ?, date = ? WHERE id = ?";

        PreparedStatement prepare = connect.prepareStatement(sql);

        prepare.setString(1, employee.getUsername());
        prepare.setString(2, employee.getPassword());
        prepare.setString(3, employee.getQuestion());
        prepare.setString(4, employee.getAnswer());

        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        prepare.setString(5, String.valueOf(sqlDate));
        prepare.setInt(6, employee.getId());

        prepare.executeUpdate();
    }

    @Override
    public void delete(Employee employee) throws SQLException {
        Connection connect = Database.connectDB();
        String sql = "DELETE FROM employee WHERE id = ?";

        PreparedStatement prepare = connect.prepareStatement(sql);

        prepare.setInt(1, employee.getId());

        prepare.executeUpdate();
    }

    @Override
    public ResultSet login(Employee employee) throws SQLException {
        Connection connect = Database.connectDB();
        String sql = "SELECT username, password FROM employee WHERE username = ? AND password = ?";

        PreparedStatement prepare = connect.prepareStatement(sql);
        prepare.setString(1, employee.getUsername());
        prepare.setString(2, employee.getPassword());

        ResultSet result = prepare.executeQuery();
        return result;
    }

    @Override
    public ResultSet isUserExist(Employee employee) throws SQLException {
        Connection connect = Database.connectDB();
        String sql = "SELECT username FROM employee WHERE username = ?";
        PreparedStatement prepare = connect.prepareStatement(sql);
        prepare.setString(1, employee.getUsername());

        ResultSet result = prepare.executeQuery();
        return result;
    }

    @Override
    public ResultSet isTrueInfo(Employee employee) throws SQLException {
        Connection connect = Database.connectDB();
        String sql = "SELECT username, question, answer FROM employee WHERE username = ? AND question = ? AND answer = ?";

        PreparedStatement prepare = connect.prepareStatement(sql);
        prepare.setString(1, employee.getUsername());
        prepare.setString(2, employee.getQuestion());
        prepare.setString(3, employee.getAnswer());

        ResultSet result = prepare.executeQuery();
        return result;
    }

    @Override
    public void updatePass(Employee employee) throws SQLException {
        Connection connect = Database.connectDB();
        String sql = "UPDATE employee SET password = ?, date = ? WHERE username = ?";

        PreparedStatement prepare = connect.prepareStatement(sql);
        prepare.setString(1, employee.getPassword());
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        prepare.setString(2, String.valueOf(sqlDate));
        prepare.setString(3, employee.getUsername());

        prepare.executeUpdate();
    }

    @Override
    public ResultSet getIDEmployee(Employee employee) throws SQLException {
        Connection connect = Database.connectDB();
        String sql = "SELECT id FROM employee WHERE username = ?";
        PreparedStatement prepare = connect.prepareStatement(sql);
        prepare.setString(1, Data.username);
        ResultSet result = prepare.executeQuery();
        return result;
    }

    @Override
    public Employee getEmployeeByUserName(String username) throws SQLException {
        Employee employee = null;
        Connection connect = Database.connectDB();
        String sql = "SELECT * FROM employee WHERE username = ?";
        PreparedStatement prepare = connect.prepareStatement(sql);
        prepare.setString(1, username);
        ResultSet result = prepare.executeQuery();
        if(result.next()){
            employee = new Employee(
                  result.getInt("id"),
                  result.getString("username"),
                  result.getString("password"),
                  result.getString("question"),
                  result.getString("answer"),
                  result.getDate("date")
            );
        }
        return employee;
    }
}
