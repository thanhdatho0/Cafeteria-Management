package com.ddt.mycafeteriamanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    public static Connection connectDB(){
        try {
            //Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx_project", "root", "thanhdat150824@");
            return connection;
        }catch (Exception e){e.printStackTrace();}
        return null;
    }
}
