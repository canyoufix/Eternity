package com.example.eternity.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;
import com.example.eternity.models.*;
import com.example.eternity.repository.EntryRepository;

public class EntryViewModel extends AndroidViewModel {

    private EntryRepository entryRepository;

    public EntryViewModel(Application application) {
        super(application);
        entryRepository = new EntryRepository(application);
    }

    public void insertPassword(Password password) {
        entryRepository.insertPassword(password);
    }

    public void insertCard(Card card) {
        entryRepository.insertCard(card);
    }

    public void insertNote(Note note) {
        entryRepository.insertNote(note);
    }

}
