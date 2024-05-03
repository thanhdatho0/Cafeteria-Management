package com.ddt.mycafeteriamanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class ProductCardDAOImpl implements ProductCardDAO{
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    @Override
    public Product get(int id) throws SQLException {
        return null;
    }

    @Override
    public ObservableList<Product> getAll() throws SQLException {
        ObservableList<Product> products = FXCollections.observableArrayList();
        Categories categories = null;
        Product product = null;
        connect = Database.connectDB();
        String sql = "SELECT * FROM product";
        prepare = connect.prepareStatement(sql);
        result = prepare.executeQuery();
        if(result.next()){
            categories = getCategories(result.getInt("categories_id"));
            product = new Product(
                    result.getInt("id"),
                    result.getString("prod_id"),
                    result.getString("prod_name"),
                    categories,
                    result.getInt("stock"),
                    result.getDouble("price"),
                    result.getString("status"),
                    result.getString("image"),
                    result.getDate("date")
            );
            products.add(product);
        }
        return products;
    }

    @Override
    public int save(Product product) throws SQLException {
        return 0;
    }

    @Override
    public void insert(Product product) throws SQLException {

    }

    @Override
//    public void update(Product product) throws SQLException {
//        connect = Database.connectDB();
//        String updateStock = "UPDATE product SET prod_name = ?, categories_id = ?, stock = ?, price = ?, status = ?, image = ?, date = ? WHERE prod_id = ?";
//        prepare = connect.prepareStatement(updateStock);
//
//        prepare.setString(1, product.getProd_name());
//        prepare.setString(2, String.valueOf(product.getCategories_id()));
//        prepare.setString(3, String.valueOf(product.getStock()));
//        prepare.setString(4, String.valueOf(product.getPrice()));
//        prepare.setString(5, product.getStatus());
//        prepare.setString(6, product.getImage());
//        prepare.setString(7, String.valueOf(product.getDate()));
//        prepare.setString(8, product.getProd_id());
//
//        prepare.executeUpdate();
//    }

    public void update(Product product) throws SQLException {
        connect = Database.connectDB();
        String updateStock = "UPDATE product SET prod_name = ?, categories_id = ?, stock = ?, price = ?, status = ?, image = ?, date = ? WHERE prod_id = ?";
        prepare = connect.prepareStatement(updateStock);

        prepare.setString(1, product.getProd_name());
        prepare.setInt(2, product.getCategories().getId());
        prepare.setInt(3, product.getStock());
        prepare.setDouble(4, product.getPrice());
        prepare.setString(5, product.getStatus());
        prepare.setString(6, product.getImage());
        prepare.setString(7, String.valueOf(product.getDate()));
        prepare.setString(8, product.getProd_id());

        prepare.executeUpdate();
    }

    @Override
    public void delete(Product product) throws SQLException {

    }

    @Override
    public ResultSet getIDProduct(Product product) throws SQLException {
        connect = Database.connectDB();
        String sql = "SELECT id FROM product WHERE prod_name = ?";
        prepare = connect.prepareStatement(sql);
        prepare.setString(1, product.getProd_name());
        result = prepare.executeQuery();
        return result;
    }

    @Override
    public Product getProductByName(String name) throws SQLException {
        Categories categories = null;
        Product product = null;
        connect = Database.connectDB();
        String sql = "SELECT * FROM product WHERE prod_name = ?";
        prepare = connect.prepareStatement(sql);
        prepare.setString(1, name);
        result = prepare.executeQuery();
        if(result.next()){
            categories = getCategories(result.getInt("categories_id"));
            product = new Product(
                    result.getInt("id"),
                    result.getString("prod_id"),
                    result.getString("prod_name"),
                    categories,
                    result.getInt("stock"),
                    result.getDouble("price"),
                    result.getString("status"),
                    result.getString("image"),
                    result.getDate("date")
            );
        }
        return product;
    }

    @Override
    public Categories getCategories(int id) throws SQLException {
        connect = Database.connectDB();
        Categories categories = null;
        String sql = "SELECT * FROM categories WHERE id = ?";
        prepare = connect.prepareStatement(sql);
        prepare.setInt(1, id);
        result = prepare.executeQuery();
        if(result.next())
            categories = new Categories(
                    result.getInt("id"),
                    result.getString("typeName")
            );
        return categories;
    }

    @Override
    public Categories getCategoriesByTypeName(String name) throws SQLException {
        connect = Database.connectDB();
        Categories categories = null;
        String sql = "SELECT * FROM categories WHERE typeName = ?";
        prepare = connect.prepareStatement(sql);
        prepare.setString(1, name);
        result = prepare.executeQuery();
        if(result.next())
            categories = new Categories(
                    result.getInt("id"),
                    result.getString("typeName")
            );
        return categories;
    }

    @Override
    public ObservableList<Product> getProductsByType(String name) throws SQLException {
        ObservableList<Product> products = FXCollections.observableArrayList();
        Product product = null;
        ProductCardDAO productCardDAO = new ProductCardDAOImpl();
        connect = Database.connectDB();
        if(name == "All"){
            products = getAll();
            return products;
        }
        String sql = "SELECT product.id, prod_id, prod_name, categories_id, stock, price, status, image, date " +
                "FROM product, categories WHERE product.categories_id = categories.id " +
                "AND categories.typeName = ?";
        prepare = connect.prepareStatement(sql);
        prepare.setString(1, name);
        result = prepare.executeQuery();
        if(result.next()){
            product = new Product(
                    result.getInt("id"),
                    result.getString("prod_id"),
                    result.getString("prod_name"),
                    productCardDAO.getCategories(result.getInt("id")),
                    result.getInt("stock"),
                    result.getDouble("price"),
                    result.getString("status"),
                    result.getString("image"),
                    result.getDate("date")
            );
            products.add(product);
        }
        return products;

    }


}
