package com.example.eternity.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.eternity.models.NoteModel;

import java.util.List;

@Dao
public interface NoteDao {
    @Insert
    void insert(NoteModel note);

    @Delete
    void delete(NoteModel note);

    @Query("SELECT * FROM notes")
    LiveData<List<NoteModel>> getAllNotes();
}
