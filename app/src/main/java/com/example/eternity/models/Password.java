package com.example.eternity.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "passwords")
public class Password {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String site;
    private String username;
    private String password;

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}