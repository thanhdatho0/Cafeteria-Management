package com.ddt.mycafeteriamanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOimpl implements ProductDAO {
    public static ProductDAOimpl getInstance()
    {
        return new ProductDAOimpl();
    }

//    @Override
    public Product get(int id) throws SQLException {
//        Connection connection = Database.connectDB();
//        Product product = null;
//        String sql = "SELECT * FROM product WHERE id = ?";
//        PreparedStatement prepare = connection.prepareStatement(sql);
//        prepare.setInt(1, id);
//
//        ResultSet res = prepare.executeQuery();
//
//        // kiem tra truy van co tra ve mot ban du lieu ko
//        if (res.next())
//        {
//            int Id = res.getInt("id");
//            String prodId = res.getString("prod_id");
//            String prodName = res.getString("prod_name");
//            int categoriesId = res.getInt("categories_id");
//            int stock = res.getInt("stock");
//            Double price = res.getDouble("price");
//            String status = res.getString("status");
//            String img = res.getString("image");
//            java.sql.Date date = res.getDate("date");
//
//            product = new Product(Id, prodId, prodName, categoriesId, stock, price, status, img, date);
//        }
        return null;
    }
    @Override
    public List<Product> getAll() throws SQLException {
        List<Product> productDataList = new ArrayList<>();
        int pId = 1;
        Product prod = get(pId);
        while(prod != null){
            productDataList.add(prod);
            pId++;
            prod = get(pId);
        }

        return productDataList;
    }
    @Override
    public int save(Product product) throws SQLException {
        return 0;
    }
    @Override
    public void insert(Product product) throws SQLException
    {
            // Tạo ket noi den CSDL
            Connection connection = Database.connectDB();
            String sql =  "insert into product(prod_id, prod_name, categories_id, stock, price, status, image, date)"
                            + "values (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement prepare = connection.prepareStatement(sql);

            prepare.setString(1, product.getProd_id());
            prepare.setString(2, product.getProd_name());
            prepare.setInt(3, product.getCategories().getId());
            prepare.setInt(4, product.getStock());
            prepare.setDouble(5, product.getPrice());
            prepare.setString(6, product.getStatus());
            prepare.setString(7, product.getImage());
            prepare.setString(8, String.valueOf(product.getDate()));

            prepare.executeUpdate();

            System.out.println("ban da :" + sql);

            Database.closePreparedStatement(prepare);
            Database.closeConnection(connection);
    }
    @Override
    public void update(Product product) throws SQLException {
            // Tạo ket noi den CSDL
            Connection connection = Database.connectDB();
            // thuc  thi cau len sql
            String sql = "UPDATE product SET prod_id = ?, prod_name = ?, categories_id = ?, stock = ?, price = ?, status = ?, image = ?, date = ? WHERE prod_id = ?";

            PreparedStatement prepare = connection.prepareStatement(sql);

            prepare.setString(1, product.getProd_id());
            prepare.setString(2, product.getProd_name());
        prepare.setInt(3, product.getCategories().getId());
            prepare.setInt(4, product.getStock());
            prepare.setDouble(5, product.getPrice());
            prepare.setString(6, product.getStatus());
            prepare.setString(7, product.getImage());
            prepare.setString(8, String.valueOf(product.getDate()));
            prepare.setString(9, product.getProd_id());

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
    public void delete(Product product) throws SQLException {
        Connection connect = Database.connectDB();
        String sql = "DELETE FROM product WHERE id = ?";
        PreparedStatement prepare = connect.prepareStatement(sql);
        prepare.setInt(1, product.getId());

        prepare.executeUpdate();
        System.out.println("ban da :" + sql);

        Database.closePreparedStatement(prepare);
        Database.closeConnection(connect);
    }

    @Override
    public ObservableList<Product> DataList() throws SQLException {
        ObservableList<Product> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM product";

        Connection connect = Database.connectDB();
        PreparedStatement prepare = connect.prepareStatement(sql);
        ResultSet result = prepare.executeQuery();

        Product prod;
        ProductCardDAO productCardDAO = new ProductCardDAOImpl();

        while(result.next()){
            prod = new Product(result.getInt("id"),
                    result.getString("prod_id"),
                    result.getString("prod_name"),
                    productCardDAO.getCategories(result.getInt("categories_id")),
                    result.getInt("stock"),
                    result.getDouble("price"),
                    result.getString("status"),
                    result.getString("image"),
                    result.getDate("date"));

            listData.add(prod);
        }
        return listData;
    }

    @Override
    public ObservableList<Product> DataTypeList(int categories_id) throws SQLException {
        ObservableList<Product> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM product WHERE categories_id = ?";

        Connection connect = Database.connectDB();
        PreparedStatement prepare = connect.prepareStatement(sql);
        prepare.setInt(1, categories_id);
        ResultSet result = prepare.executeQuery();

        Product prod;
        ProductCardDAO productCardDAO = new ProductCardDAOImpl();

        while(result.next()){
            prod = new Product(result.getInt("id"),
                    result.getString("prod_id"),
                    result.getString("prod_name"),
                    productCardDAO.getCategories(result.getInt("categories_id")),
                    result.getInt("stock"),
                    result.getDouble("price"),
                    result.getString("status"),
                    result.getString("image"),
                    result.getDate("date"));

            listData.add(prod);
        }
        return listData;
    }

}
