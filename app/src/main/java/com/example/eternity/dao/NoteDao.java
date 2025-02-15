package com.example.eternity.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.eternity.models.Note;

import java.util.List;

@Dao
public interface NoteDao {
    @Insert
    void insert(Note note);

    @Delete
    void delete(Note note);

    @Query("SELECT * FROM notes")
    List<Note> getAllNotes();
}
