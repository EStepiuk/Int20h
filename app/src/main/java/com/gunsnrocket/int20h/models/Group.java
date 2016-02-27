package com.gunsnrocket.int20h.models;

/**
 * Created by Kryvonis on 2/27/16.
 */
public class Group {
    int id;
    String name;
    int points;
    int id_Cat;

    public Group(int id, String name, int points, int id_Cat) {
        this.id = id;
        this.name = name;
        this.points = points;
        this.id_Cat = id_Cat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getId_Cat() {
        return id_Cat;
    }

    public void setId_Cat(int id_Cat) {
        this.id_Cat = id_Cat;
    }
}
