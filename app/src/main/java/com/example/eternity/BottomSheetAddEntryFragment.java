package com.example.eternity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.lifecycle.ViewModelProvider;

import com.example.eternity.R;
import com.example.eternity.models.CardModel;
import com.example.eternity.models.NoteModel;
import com.example.eternity.models.PasswordModel;
import com.example.eternity.viewmodel.EntryViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetAddEntryFragment extends BottomSheetDialogFragment {

    private Spinner spinnerType;
    private EditText etTitle, etSite, etUsername, etPassword, etCardNumber, etCardHolder, etCardExpiry, etCardCVC, etNoteContent;
    private Button btnSave, btnCancel;
    private EntryViewModel entryViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bottom_sheet_add_entry, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Инициализация всех элементов
        spinnerType = view.findViewById(R.id.spinnerType);
        etTitle = view.findViewById(R.id.etTitle);
        etSite = view.findViewById(R.id.etSite);
        etUsername = view.findViewById(R.id.etUsername);
        etPassword = view.findViewById(R.id.etPassword);
        etCardNumber = view.findViewById(R.id.etCardNumber);
        etCardHolder = view.findViewById(R.id.etCardHolder);
        etCardExpiry = view.findViewById(R.id.etCardExpiry);
        etCardCVC = view.findViewById(R.id.etCardCVC);
        etNoteContent = view.findViewById(R.id.etNoteContent);
        btnSave = view.findViewById(R.id.btnSave);
        btnCancel = view.findViewById(R.id.btnCancel);

        // Инициализация ViewModel с использованием фабрики
        entryViewModel = new ViewModelProvider(this).get(EntryViewModel.class);

        // Адаптер для Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.entry_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(adapter);

        // Обработчик выбора в Spinner
        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                hideAllFields();  // Скрываем все поля

                switch (position) {
                    case 0: // Пароль
                        showPasswordFields();
                        break;
                    case 1: // Карта
                        showCardFields();
                        break;
                    case 2: // Защищенная заметка
                        showNoteFields();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Можно оставить пустым
            }
        });

        // Обработчик кнопки "Сохранить"
        btnSave.setOnClickListener(v -> {
            switch (spinnerType.getSelectedItemPosition()) {
                case 0: // Пароль
                    PasswordModel password = new PasswordModel();
                    password.setTitle(etTitle.getText().toString());  // Используем новое поле Title
                    password.setSite(etSite.getText().toString());
                    password.setUsername(etUsername.getText().toString());
                    password.setPassword(etPassword.getText().toString());
                    Log.d("Insert", "Password: " + password.getSite() + ", " + password.getUsername());
                    entryViewModel.insertPassword(password);
                    break;

                case 1: // Карта
                    CardModel card = new CardModel();
                    card.setTitle(etTitle.getText().toString());  // Используем новое поле Title
                    card.setCardNumber(etCardNumber.getText().toString());
                    card.setCardHolder(etCardHolder.getText().toString());
                    card.setExpiryDate(etCardExpiry.getText().toString());
                    card.setCvc(etCardCVC.getText().toString());
                    entryViewModel.insertCard(card);
                    break;

                case 2: // Защищенная заметка
                    NoteModel note = new NoteModel();
                    note.setTitle(etTitle.getText().toString());  // Используем новое поле Title
                    note.setContent(etNoteContent.getText().toString());
                    entryViewModel.insertNote(note);
                    break;
            }
            dismiss();  // Закрыть BottomSheet
        });

        // Обработчик кнопки "Отмена"
        btnCancel.setOnClickListener(v -> dismiss());
    }

    // Метод для скрытия всех полей
    private void hideAllFields() {
        etSite.setVisibility(View.GONE);
        etUsername.setVisibility(View.GONE);
        etPassword.setVisibility(View.GONE);
        etCardNumber.setVisibility(View.GONE);
        etCardHolder.setVisibility(View.GONE);
        etCardExpiry.setVisibility(View.GONE);
        etCardCVC.setVisibility(View.GONE);
        etNoteContent.setVisibility(View.GONE);
    }

    // Метод для показа полей для пароля
    private void showPasswordFields() {
        etSite.setVisibility(View.VISIBLE);
        etUsername.setVisibility(View.VISIBLE);
        etPassword.setVisibility(View.VISIBLE);
    }

    // Метод для показа полей для карты
    private void showCardFields() {
        etCardNumber.setVisibility(View.VISIBLE);
        etCardHolder.setVisibility(View.VISIBLE);
        etCardExpiry.setVisibility(View.VISIBLE);
        etCardCVC.setVisibility(View.VISIBLE);
    }

    // Метод для показа полей для защищенной заметки
    private void showNoteFields() {
        etNoteContent.setVisibility(View.VISIBLE);
    }
}

