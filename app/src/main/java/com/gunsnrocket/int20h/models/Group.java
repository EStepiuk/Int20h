package com.gunsnrocket.int20h.models;

/**
 * Created by Kryvonis on 2/27/16.
 */
public class Group {
    Integer id;
    String name;
    Integer points;
    Integer id_Cat;

    public Group(Integer id, String name, Integer points, Integer id_Cat) {
        this.id = id;
        this.name = name;
        this.points = points;
        this.id_Cat = id_Cat;
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

    public Integer getId_Cat() {
        return id_Cat;
    }

    public void setId_Cat(Integer id_Cat) {
        this.id_Cat = id_Cat;
    }
}
