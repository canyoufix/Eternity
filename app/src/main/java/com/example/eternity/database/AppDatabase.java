package com.example.eternity.database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.eternity.dao.CardDao;
import com.example.eternity.dao.NoteDao;
import com.example.eternity.dao.PasswordDao;

import com.example.eternity.models.Card;
import com.example.eternity.models.Note;
import com.example.eternity.models.Password;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



@Database(entities = {Password.class, Card.class, Note.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    public abstract PasswordDao passwordDao();
    public abstract CardDao cardDao();
    public abstract NoteDao noteDao();

    // Executor для записи в базу данных
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(4);

    public static synchronized AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app_database")
                    .addCallback(new RoomDatabase.Callback() {
                        @Override
                        public void onOpen(@NonNull SupportSQLiteDatabase db) {
                            super.onOpen(db);
                            Log.d("Database", "Database opened.");
                        }
                    })
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}
