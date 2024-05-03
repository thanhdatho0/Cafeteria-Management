package com.ddt.mycafeteriamanagementsystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriesDAOImpl implements CategoriesDAO{
    Connection connect;
    PreparedStatement prepare;
    ResultSet result;

    @Override
    public Categories get(int id) throws SQLException {
        connect = Database.connectDB();
        Categories categories = null;
        String sql = "SELECT * FROM categories WHERE id = ?";
        prepare.setInt(1, id);
        prepare = connect.prepareStatement(sql);
        result = prepare.executeQuery();

        if(result.next()){
            int categories_id = result.getInt("id");
            String typeName = result.getString("typeName");

            categories = new Categories(categories_id, typeName);
        }
        return categories;
    }

    @Override
    public List<Categories> getAll() throws SQLException {
        List<Categories> categoriesList = new ArrayList<>();
        Categories categories = null;
        connect = Database.connectDB();
        String sql = "SELECT * FROM categories";
        prepare = connect.prepareStatement(sql);
        result = prepare.executeQuery();
        if(result.next()){
            categories = new Categories(
                    result.getInt("id"),
                    result.getString("typeName")
            );
            categoriesList.add(categories);
        }
        return categoriesList;
    }

    @Override
    public int save(Categories categories) throws SQLException {
        return 0;
    }

    @Override
    public void insert(Categories categories) throws SQLException {
        connect = Database.connectDB();
        String sql = "INSERT INTO categories(typeName) VALUES(?)";
        prepare = connect.prepareStatement(sql);
        prepare.setString(1, categories.getTypeName());

        prepare.executeUpdate();
    }

    @Override
    public void update(Categories categories) throws SQLException {

    }

    @Override
    public void delete(Categories categories) throws SQLException {

    }

    public static boolean checkCategories(Categories categories) throws SQLException {
        boolean categoriesExists = false;

        Connection connect = Database.connectDB();
        String sql = "SELECT * FROM categories WHERE typeName = ?";
        PreparedStatement prepare;
        prepare = connect.prepareStatement(sql);
        prepare.setString(1, categories.getTypeName());
        ResultSet result = prepare.executeQuery();

        String cate_typeName;
        if(result.next()){
            cate_typeName = result.getString("typeName");
            if(cate_typeName.equals(categories.getTypeName())){
                categoriesExists = true;
            }
        }
        return categoriesExists;
    }

    @Override
    public ResultSet getAllCate(Categories categories) throws SQLException {
        connect = Database.connectDB();
        String sql = "SELECT * FROM categories";
        prepare = connect.prepareStatement(sql);
        result = prepare.executeQuery();

        return result;
    }

    @Override
    public List<Product> getAllProduct(Categories categories) throws SQLException{
        List<Product> productList = new ArrayList<>();
        Product product = null;
        connect = Database.connectDB();
        String sql = "SELECT * FROM product WHERE categories_id = ?";
        prepare.setInt(1, categories.getId());
        prepare = connect.prepareStatement(sql);
        result = prepare.executeQuery();
        if(result.next()){
            product = new Product(
                    result.getInt( "id"),
                    result.getString("prod_id"),
                    result.getString("prod_name"),
                    categories,
                    result.getInt("stock"),
                    result.getDouble("price"),
                    result.getString("status"),
                    result.getString("image"),
                    result.getDate("date")
            );
            productList.add(product);
        }
        return productList;
    }
}
