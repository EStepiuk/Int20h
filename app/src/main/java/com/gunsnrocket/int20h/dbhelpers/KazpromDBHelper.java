package com.gunsnrocket.int20h.dbhelpers;

import android.util.Log;

import com.gunsnrocket.int20h.models.Category;
import com.gunsnrocket.int20h.models.Group;
import com.gunsnrocket.int20h.models.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


/**
 * Created by Kryvonis on 2/27/16.
 */
public class KazpromDBHelper {
    private final String USER_NAME = "postgres";
    private final String USER_PASWORD = "postgres";
    private final String DB_URL = "jdbc:postgresql://192.168.20.2:5432/kazprom";
    private final String DRIVER = "org.postgresql.Driver";

    private Connection connection;


    private KazpromDBHelper() {
    }

    private static KazpromDBHelper instance = new KazpromDBHelper();

    public static KazpromDBHelper getInstance() {
        return instance;
    }


    public void connect() {
        try {
            if(connection == null) {
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(DB_URL, USER_NAME, USER_PASWORD);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        if (connection != null) try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getCategoryList(ArrayList<Category> resultList) {

        String sql = "Select * from product_category\n" +
                "where main_parent_category_id = 0\n" +
                "GROUP BY caption,id;";
        ResultSet rs;
        Statement statement;
        try {
            statement = connection.createStatement();
            Log.d("Test", "BEFORE QUERY");
            rs = statement.executeQuery(sql);
            Log.d("Test", "After QUERY");
            while (rs.next()) {
                Log.d("TAG", rs.getString("caption"));
                /**
                 * MATEMATIKA
                 *
                 *
                 */
                Category category = new Category(rs.getInt("id"), rs.getString("caption"), 0);
                resultList.add(category);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getGroupList(ArrayList<Group> resultList, int idCategory) {

        String sql = "Select * from product_category\n" +
                "  WHERE main_parent_category_id = ?;";
        ResultSet rs;
        PreparedStatement statement;
        try {

            statement = connection.prepareStatement(sql);
            statement.setInt(1, idCategory);
            rs = statement.executeQuery();
            while (rs.next()) {
                Log.d("TAG", rs.getString("caption"));
                /**
                 * MATEMATIKA
                 *
                 *
                 */
                Group category = new Group(rs.getInt("id"), rs.getString("caption"), 0, idCategory);
                resultList.add(category);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getProductList(ArrayList<Product> resultList, int idCategory, int idGroup) {

        String sql = "Select * FROM product\n" +
                "WHERE category_id = ? or category_id = ?;";
        ResultSet rs;
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idCategory);
            statement.setInt(2, idGroup);
            rs = statement.executeQuery(sql);

            while (rs.next()) {
                Log.d("TAG", rs.getString("caption"));
                String desc = rs.getString("html_long_description");
                Product category = new Product(rs.getInt("id"), rs.getString("caption"), idGroup,
                        desc.substring(3,desc.length()-3));
                resultList.add(category);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param group
     * @param ids
     * @return
     */
    public Product getProductReclam(Group group,ArrayList<Integer> ids) {

        String sql = "Select * FROM product\n" +
                "WHERE category_id = ? and rank  = (Select max(rank) FROM product WHERE " +
                "category_id = ?)";
        ResultSet rs;
        Product product = null;
        PreparedStatement statement;
        try {
            for (int i = 0; i < ids.size(); i++) {
                sql += " and id <> " + ids.get(i);
            }
            sql += ";";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, group.getId());
            statement.setInt(2, group.getId());
            rs = statement.executeQuery();
            while (rs.next()) {
                Log.d("TTAG", rs.getString("name"));
                String desc = rs.getString("html_long_description");
                product = new Product(rs.getInt("id"), rs.getString("name"), group.getId(),
                        desc.substring(3,desc.length()-3));
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

}


