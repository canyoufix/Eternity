package com.example.eternity.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.eternity.models.*;
import com.example.eternity.repository.EntryRepository;

import java.util.List;

public class EntryViewModel extends AndroidViewModel {

    private EntryRepository entryRepository;

    public EntryViewModel(Application application) {
        super(application);
        entryRepository = new EntryRepository(application);
    }

    public void insertPassword(PasswordModel password) {
        entryRepository.insertPassword(password);
    }

    public void insertCard(CardModel card) {
        entryRepository.insertCard(card);
    }

    public void insertNote(NoteModel note) {
        entryRepository.insertNote(note);
    }

    public LiveData<List<PasswordModel>> getAllPasswords() {
        return entryRepository.getAllPasswords();
    }

    public LiveData<List<CardModel>> getAllCards() {
        return entryRepository.getAllCards();
    }

    public LiveData<List<NoteModel>> getAllNotes() {
        return entryRepository.getAllNotes();
    }
}
