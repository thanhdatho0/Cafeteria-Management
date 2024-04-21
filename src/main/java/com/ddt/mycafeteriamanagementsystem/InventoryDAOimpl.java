package com.ddt.mycafeteriamanagementsystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryDAOimpl implements ProductDAO {
    public static InventoryDAOimpl getInstance()
    {
        return new InventoryDAOimpl();
    }

    @Override
    public ProductData get(int id) throws SQLException {
        Connection connection = Database.connectDB();
        ProductData productData = null;
        String sql = "SELECT * FROM product WHERE id = ?";
        PreparedStatement prepare = connection.prepareStatement(sql);
        prepare.setInt(1, id);

        ResultSet res = prepare.executeQuery();

        // kiem tra truy van co tra ve mot ban du lieu ko
        if (res.next())
        {
            int Id = res.getInt("id");
            String prodId = res.getString("prod_id");
            String prodName = res.getString("prod_name");
            String type = res.getString("type");
            int stock = res.getInt("stock");
            Double price = res.getDouble("price");
            String status = res.getString("status");
            String img = res.getString("image");
            java.sql.Date date = res.getDate("date");

            productData = new ProductData(Id, prodId, prodName, type, stock, price, status, img, date);
        }
        return productData;
    }
    @Override
    public List<ProductData> getAll() throws SQLException {
        List<ProductData> productDataList = new ArrayList<>();
        int pId = 1;
        ProductData prod = get(pId);
        while(prod != null){
            productDataList.add(prod);
            pId++;
            prod = get(pId);
        }

        return productDataList;
    }
    @Override
    public int save(ProductData productData) throws SQLException {
        return 0;
    }
    @Override
    public void insert(ProductData productData) throws SQLException
    {
            // Tạo ket noi den CSDL
            Connection connection = Database.connectDB();
            String sql =  "insert into product(prod_id, prod_name, type, stock, price, status, image, date)"
                            + "values (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement prepare = connection.prepareStatement(sql);

            prepare.setString(1, productData.getProductID());
            prepare.setString(2, productData.getProductName());
            prepare.setString(3, productData.getType());
            prepare.setInt(4, productData.getStock());
            prepare.setDouble(5, productData.getPrice());
            prepare.setString(6, productData.getStatus());
            prepare.setString(7, productData.getImage());
            prepare.setString(8, String.valueOf(productData.getDate()));

            prepare.executeUpdate();

            System.out.println("ban da :" + sql);

            Database.closePreparedStatement(prepare);
            Database.closeConnection(connection);
    }
    @Override
    public void update(ProductData productData) throws SQLException {
            // Tạo ket noi den CSDL
            Connection connection = Database.connectDB();
            // thuc  thi cau len sql
            String sql = "UPDATE product SET prod_id = ?, prod_name = ?, type = ?, stock = ?, price = ?, status = ?, image = ?, date = ? WHERE prod_id = ?";

            PreparedStatement prepare = connection.prepareStatement(sql);

            prepare.setString(1, productData.getProductID());
            prepare.setString(2, productData.getProductName());
            prepare.setString(3, productData.getType());
            prepare.setInt(4, productData.getStock());
            prepare.setDouble(5, productData.getPrice());
            prepare.setString(6, productData.getStatus());
            prepare.setString(7, productData.getImage());
            prepare.setString(8, String.valueOf(productData.getDate()));
            prepare.setString(9, productData.getProductID());

            prepare.executeUpdate();
            System.out.println("ban da :" + sql);

            Database.closePreparedStatement(prepare);
            Database.closeConnection(connection);
    }
    @Override
    public ResultSet check(String id) throws SQLException {
        Connection connect = Database.connectDB();
        String sql = "SELECT prod_id FROM product WHERE prod_id = ?";
        PreparedStatement prepare = connect.prepareStatement(sql);
        prepare.setString(1, id);

        ResultSet res = prepare.executeQuery();
        return res;
    }
    @Override
    public void delete(ProductData productData) throws SQLException {
        Connection connect = Database.connectDB();
        String sql = "DELETE FROM product WHERE id = ?";
        PreparedStatement prepare = connect.prepareStatement(sql);
        prepare.setInt(1, productData.getId());

        prepare.executeUpdate();
        System.out.println("ban da :" + sql);

        Database.closePreparedStatement(prepare);
        Database.closeConnection(connect);
    }
}
