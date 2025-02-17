package com.example.eternity.ui.storage;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eternity.R;
import com.example.eternity.adapter.PasswordAdapter;
import com.example.eternity.viewmodel.EntryViewModel;


public class PasswordFragment extends Fragment {

    private RecyclerView recyclerView;
    private PasswordAdapter passwordAdapter;
    private EntryViewModel entryViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_password, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Получаем доступ к Toolbar через NavController
        if (getActivity() != null) {
            AppCompatActivity activity = (AppCompatActivity) getActivity();
            if (activity.getSupportActionBar() != null) {
                // Показываем стрелку "назад" в ActionBar
                activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
            }
        }

        // Инициализация RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        passwordAdapter = new PasswordAdapter();
        recyclerView.setAdapter(passwordAdapter);

        // Инициализация ViewModel
        entryViewModel = new ViewModelProvider(this).get(EntryViewModel.class);

        // Наблюдаем за LiveData
        entryViewModel.getAllPasswords().observe(getViewLifecycleOwner(), passwords -> {
            // Обновляем адаптер, когда данные изменяются
            passwordAdapter.submitList(passwords);
        });
    }
}
