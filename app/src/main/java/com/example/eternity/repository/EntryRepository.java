package com.example.eternity.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.eternity.database.AppDatabase;
import com.example.eternity.dao.PasswordDao;
import com.example.eternity.dao.CardDao;
import com.example.eternity.dao.NoteDao;
import com.example.eternity.models.CardModel;
import com.example.eternity.models.PasswordModel;
import com.example.eternity.models.NoteModel;

import java.util.List;


public class EntryRepository {

    private final PasswordDao passwordDao;
    private final CardDao cardDao;
    private final NoteDao noteDao;
    private final AppDatabase database;  // Храним инстанс базы данных

    public EntryRepository(Application application) {
        database = AppDatabase.getInstance(application);
        passwordDao = database.passwordDao();
        cardDao = database.cardDao();
        noteDao = database.noteDao();
    }

    // Асинхронная вставка пароля с транзакцией
    public void insertPassword(PasswordModel password) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            database.runInTransaction(() -> {  // Используем сохраненный экземпляр базы
                passwordDao.insert(password);
            });
        });
    }

    // Асинхронная вставка карты с транзакцией
    public void insertCard(CardModel card) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            database.runInTransaction(() -> {
                cardDao.insert(card);
            });
        });
    }

    // Асинхронная вставка заметки с транзакцией
    public void insertNote(NoteModel note) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            database.runInTransaction(() -> {
                noteDao.insert(note);
            });
        });
    }

    public LiveData<List<PasswordModel>> getAllPasswords() {
        return passwordDao.getAllPasswords();  // Получаем все пароли через DAO
    }

    public LiveData<List<CardModel>> getAllCards() {
        return cardDao.getAllCards();
    }

    public LiveData<List<NoteModel>> getAllNotes() {
        return noteDao.getAllNotes();
    }
}

