package com.example.eternity.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.eternity.models.CardModel;

import java.util.List;

@Dao
public interface CardDao {
    @Insert
    void insert(CardModel card);

    @Delete
    void delete(CardModel card);

    @Query("SELECT * FROM cards")
    LiveData<List<CardModel>> getAllCards();
}
