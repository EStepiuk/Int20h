package com.gunsnrocket.int20h.dbhelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.gunsnrocket.int20h.models.Category;
import com.gunsnrocket.int20h.models.Group;
import com.gunsnrocket.int20h.models.Product;

import java.util.ArrayList;

/**
 * Created by dmytro on 27.02.16.
 */
public class LocalDbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "int20h";
    private static final int DB_VERSION = 2;
    private static final String CREATE_SQL = "CREATE TABLE category (\n" +
            "  id INTEGER NOT NULL,\n" +
            "  name VARCHAR(45),\n" +
            "  points INTEGER,\n" +
            "  PRIMARY KEY (id));";


    private final String CREATE_SQL_GROUP = "CREATE TABLE kazgroup (\n" +
            " id INTEGER NOT NULL,\n" +
            "  name VARCHAR(45),\n" +
            "  points INTEGER,\n" +
            "  id_Cat INTEGER,\n" +
            "  PRIMARY KEY (id));";
    private final String CREATE_SQL_PROD = "CREATE TABLE product (\n" +
            "  id INTEGER NOT NULL,\n" +
            "  name VARCHAR(45),\n" +
            "  points INTEGER,\n" +
            "  id_group INTEGER,\n" +
            "  PRIMARY KEY (id));";
    private final String CATEGORY_TABLE_NAME = "category";
    private final String GROUP_TABLE_NAME = "kazgroup";
    private final String PRODUCT_TABLE_NAME = "product";

    public LocalDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_SQL);
        db.execSQL(CREATE_SQL_GROUP);
        db.execSQL(CREATE_SQL_PROD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public int getMaxCategoryId(){
        String sql = "Select * FROM "+CATEGORY_TABLE_NAME+"\n" +
                "WHERE points = (Select max(points) FROM "+CATEGORY_TABLE_NAME+")";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor != null)
            cursor.moveToFirst();
        return cursor.getInt(cursor.getColumnIndex("id"));
    }
    public ArrayList<Integer> getIdListProduct(int id){
        ArrayList<Integer> resList = new ArrayList<>();
        String sql = "select id from " + PRODUCT_TABLE_NAME +
                " where id_group = "+id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                resList.add(cursor.getInt(cursor.getColumnIndex("id")));
            }while (cursor.moveToNext());
        }
        return resList;
    }
    public void addCategory(Category category){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", category.getId());
        values.put("name", category.getName());
        values.put("points", 0);
        db.insert(CATEGORY_TABLE_NAME, null, values);
        db.close();
    }
    public void addGroup(Group group){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", group.getId());
        values.put("name", group.getName());
        values.put("points", 0);
        values.put("id_Cat", group.getId_Cat());
        db.insert(GROUP_TABLE_NAME, null, values);
        db.close();
    }

    public void addProduct(Product product){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", product.getId());
        values.put("name", product.getName());
        values.put("points", 0);
        values.put("id_group", product.getId_Group());
        db.insert(PRODUCT_TABLE_NAME, null, values);
    }

    public boolean isCategoryExist(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "select * from " + CATEGORY_TABLE_NAME + "where id = " + id;
        Cursor cursor = db.rawQuery(sql, null);
        return cursor.isFirst();
    }

    public boolean isGroupExist(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "select * from " + GROUP_TABLE_NAME + "where id = " + id;
        Cursor cursor = db.rawQuery(sql, null);
        return cursor.isFirst();
    }

    public boolean isProductExist(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "select * from " + PRODUCT_TABLE_NAME + "where id = " + id;
        Cursor cursor = db.rawQuery(sql, null);
        return cursor.isFirst();
    }
}
