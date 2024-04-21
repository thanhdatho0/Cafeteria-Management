package com.ddt.mycafeteriamanagementsystem;

import java.sql.*;

public class Database {
    public static Connection connectDB(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx_project", "root", "");
            return connection;
        }catch (Exception e){e.printStackTrace();}
        return null;
    }

    public static void closeConnection(Connection c)
    {
        try {
            if (c != null)
                c.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void closePreparedStatement(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.close();
    }

    public static void closeResultSet(ResultSet resultSet) throws SQLException {
        resultSet.close();
    }

}
