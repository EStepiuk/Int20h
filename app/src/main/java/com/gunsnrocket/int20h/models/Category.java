package com.gunsnrocket.int20h.models;

/**
 * Created by Kryvonis on 2/27/16.
 */
public class Category {
    Integer id;
    String name;
    Integer points;

    public Category(Integer id, String name, Integer points) {
        this.id = id;
        this.name = name;
        this.points = points;
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

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
