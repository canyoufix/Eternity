package com.example.eternity.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.eternity.models.Password;

import java.util.List;

@Dao
public interface PasswordDao {
    @Insert
    void insert(Password password);

    @Delete
    void delete(Password password);

    @Query("SELECT * FROM passwords")
    List<Password> getAllPasswords();
}
