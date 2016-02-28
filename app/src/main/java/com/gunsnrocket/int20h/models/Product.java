package com.gunsnrocket.int20h.models;

/**
 * Created by Kryvonis on 2/27/16.
 */
public class Product {
    int id;
    String name;
    int id_Group;
    String desc;

    public Product(int id, String name, int id_Group,String desc) {
        this.id = id;
        this.name = name;
        this.id_Group = id_Group;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public int getId_Group() {
        return id_Group;
    }

    public void setId_Group(Integer id_Group) {
        this.id_Group = id_Group;
    }
}
