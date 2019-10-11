package ru.zankov.model;

import java.io.Serializable;

public class Category implements Serializable {

    private int id;
    private String name;
    private String type;
    private String createdAt;
    private User user;

    /*public Category(int id, String name, String type, String createdAt, User user) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.createdAt = createdAt;
        this.user = user;
    }*/

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}