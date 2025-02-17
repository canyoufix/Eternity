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
import com.example.eternity.adapter.CardAdapter;
import com.example.eternity.viewmodel.EntryViewModel;


public class CardFragment extends Fragment {

    private RecyclerView recyclerView;
    private CardAdapter cardAdapter;
    private EntryViewModel entryViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_card, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Настройка ActionBar с кнопкой "Назад"
        if (getActivity() != null) {
            AppCompatActivity activity = (AppCompatActivity) getActivity();
            if (activity.getSupportActionBar() != null) {
                activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
            }
        }

        // Инициализация RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        cardAdapter = new CardAdapter();
        recyclerView.setAdapter(cardAdapter);

        // Инициализация ViewModel
        entryViewModel = new ViewModelProvider(this).get(EntryViewModel.class);

        // Наблюдаем за LiveData карт
        entryViewModel.getAllCards().observe(getViewLifecycleOwner(), cards -> {
            cardAdapter.submitList(cards);
        });
    }
}
