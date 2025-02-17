package com.example.eternity.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "passwords")
public class PasswordModel {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PasswordModel that = (PasswordModel) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, username, password);
    }
}