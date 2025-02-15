package com.example.eternity.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.eternity.models.Card;

import java.util.List;

@Dao
public interface CardDao {
    @Insert
    void insert(Card card);

    @Delete
    void delete(Card card);

    @Query("SELECT * FROM cards")
    List<Card> getAllCards();
}
