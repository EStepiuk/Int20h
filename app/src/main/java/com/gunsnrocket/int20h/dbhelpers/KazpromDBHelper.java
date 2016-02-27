package com.gunsnrocket.int20h.dbhelpers;

import android.util.Log;

import com.gunsnrocket.int20h.models.Category;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kryvonis on 2/27/16.
 */
public class KazpromDBHelper {
    private final String USER_NAME = "postgres";
    private final String USER_PASWORD = "postgres";
    private final String DB_URL = "jdbc:postgresql://192.168.20.2:5432/kazprom";
    private final String DRIVER = "org.postgresql.Driver";

    private Connection connection;
    private Statement stmt;

    private KazpromDBHelper(){}
    private static KazpromDBHelper instance = new KazpromDBHelper();

    static KazpromDBHelper getInstance(){return instance;}


    public void connect(){
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER_NAME, USER_PASWORD);
            stmt = connection.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close(){
        if(connection != null) try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getCategoryList(ArrayList<Category> resultList){

        String sql = "Select * from product_category\n" +
                "where main_parent_category_id = 0\n" +
                "GROUP BY caption,id;";
        ResultSet rs;

        try {
            Log.d("Test","BEFORE QUERY");
            rs = stmt.executeQuery(sql);
            Log.d("Test","After QUERY");
            while (rs.next()){
                Log.d("TAG", rs.getString("caption"));
                /**
                 * MATEMATIKA
                 *
                 *
                 */
                Category category = new Category(rs.getInt("id"),rs.getString("caption"),0);
                resultList.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

