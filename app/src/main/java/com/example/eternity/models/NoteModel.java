package com.example.eternity.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "notes")
public class NoteModel {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String content;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        NoteModel that = (NoteModel) obj;
        return Objects.equals(title, that.title);  // Сравниваем заметки по title
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);  // Используем title для вычисления hashCode
    }

}

