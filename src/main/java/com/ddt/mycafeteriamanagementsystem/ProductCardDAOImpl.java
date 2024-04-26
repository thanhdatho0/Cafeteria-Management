package com.ddt.mycafeteriamanagementsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public List<Product> getAll() throws SQLException {
        return null;
    }

    @Override
    public int save(Product product) throws SQLException {
        return 0;
    }

    @Override
    public void insert(Product product) throws SQLException {

    }

    @Override
    public void update(Product product) throws SQLException {
        connect = Database.connectDB();
        String updateStock = "UPDATE product SET prod_name = ?, categories_id = ?, stock = ?, price = ?, status = ?, image = ?, date = ? WHERE prod_id = ?";
        prepare = connect.prepareStatement(updateStock);

        prepare.setString(1, product.getProd_name());
        prepare.setString(2, String.valueOf(product.getCategories_id()));
        prepare.setString(3, String.valueOf(product.getStock()));
        prepare.setString(4, String.valueOf(product.getPrice()));
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
}
