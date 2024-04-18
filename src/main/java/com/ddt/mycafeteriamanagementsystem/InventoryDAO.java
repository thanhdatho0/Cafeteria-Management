package com.ddt.mycafeteriamanagementsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class InventoryDAO implements DAOinterface<ProductData> {

    public static InventoryDAO getInstance()
    {
        return new InventoryDAO();
    }
    @Override
    public void insert(ProductData productData) {
        try {
            // Tạo ket noi den CSDL
            Connection connection = Database.connectDB();
            // tao doi tuong statement
            Statement statement = connection.createStatement();
            // thuc  thi cau len sql

            String sql =  "insert into product "
                    + "(prod_id, prod_name, type, stock, price, status, image, date) "
                    + "values ('"
                    + productData.getProductID() + "', '"
                    + productData.getProductName() + "', '"
                    + productData.getType() + "', '"
                    + productData.getStock() + "', '"
                    + productData.getPrice() + "', '"
                    + productData.getStatus() + "', '"
                    + productData.getImage() + "', '"
                    + productData.getDate() + "')";

//            String sql =  "insert into product "
//                            + "(prod_id, prod_name, type, stock, price, status, image, date) "
//                            + "values (?, ?, ?, ?, ?, ?, ?, ?)";
//
//            PreparedStatement prepare = connection.prepareStatement(sql);
//
//            prepare.setString(1, productData.getProductID());
//            prepare.setString(2, productData.getProductName());
//            prepare.setString(3, productData.getType());
//            prepare.setInt(4, productData.getStock());
//            prepare.setDouble(5, productData.getPrice());
//            prepare.setString(6, productData.getStatus());
//            prepare.setString(7, productData.getImage());
//            prepare.setDate(8, productData.getDate());
//            prepare.executeUpdate();

            statement.executeUpdate(sql);

            System.out.printf("ban da :" + sql);

            Database.closeConnection(connection);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ProductData productData) {
        try {
            // Tạo ket noi den CSDL
            Connection connection = Database.connectDB();
            // tao doi tuong statement
            Statement statement = connection.createStatement();
            // thuc  thi cau len sql
//            String sql = "UPDATE product SET prod_id = ?, prod_name = ?, type = ?, stock = ?, price = ?, status = ?, image = ?, date = ? WHERE id = ?";

//            PreparedStatement prepare = connection.prepareStatement(sql);
//
//            prepare.setString(1, productData.getProductID());
//            prepare.setString(2, productData.getProductName());
//            prepare.setString(3, productData.getType());
//            prepare.setInt(4, productData.getStock());
//            prepare.setDouble(5, productData.getPrice());
//            prepare.setString(6, productData.getStatus());
//            prepare.setString(7, productData.getImage());
//            prepare.setString(8, String.valueOf(productData.getDate()));
//            prepare.setInt(9, Data.id);
//
//            prepare.executeUpdate();

            String sql = "UPDATE product SET prod_id = '" + productData.getProductID() +
                    "', prod_name = '" + productData.getProductName() +
                    "', type = '" + productData.getType() +
                    "', stock = '" + productData.getStock() +
                    "', price = '" + productData.getPrice() +
                    "', status = '" + productData.getStatus() +
                    "', image = '" + productData.getImage() +
                    "', date = '" + productData.getDate() + "' WHERE id = '" + Data.id + "'";


            statement.executeUpdate(sql);

            System.out.printf("ban da :" + sql);

            Database.closeConnection(connection);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public int delete(ProductData productData) {
        return 0;
    }
}
