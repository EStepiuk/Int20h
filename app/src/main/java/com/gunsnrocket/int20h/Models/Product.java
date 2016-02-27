package com.gunsnrocket.int20h.models;

/**
 * Created by Kryvonis on 2/27/16.
 */
public class Product {
    Integer id;
    String name;
    Integer id_Group;

    public Product(Integer id, String name, Integer id_Group) {
        this.id = id;
        this.name = name;
        this.id_Group = id_Group;
    }

    public Integer getId() {
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

    public Integer getId_Group() {
        return id_Group;
    }

    public void setId_Group(Integer id_Group) {
        this.id_Group = id_Group;
    }
}
