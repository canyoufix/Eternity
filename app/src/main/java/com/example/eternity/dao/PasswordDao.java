package com.example.eternity.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.eternity.models.PasswordModel;

import java.util.List;

@Dao
public interface PasswordDao {
    @Insert
    void insert(PasswordModel password);

    @Delete
    void delete(PasswordModel password);

    @Query("SELECT * FROM passwords")
    LiveData<List<PasswordModel>> getAllPasswords();
}
