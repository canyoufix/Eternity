package com.example.eternity.ui.main;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.eternity.R;
import com.example.eternity.adapter.CategoryAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.eternity.ui.BottomSheetAddEntryFragment;

import java.util.Arrays;
import java.util.List;


public class Storage extends Fragment {

    private RecyclerView recyclerCategories;
    private CategoryAdapter categoryAdapter;
    private NavController navController; // Объявляем NavController

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_storage, container, false);

        recyclerCategories = view.findViewById(R.id.recyclerCategories);
        recyclerCategories.setLayoutManager(new LinearLayoutManager(getContext()));

        // Инициализация NavController
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        // Список категорий без отдельного класса
        List<String> categories = Arrays.asList(
                "Логин", "Карта", "Защищенная заметка"
        );

        // Устанавливаем адаптер
        categoryAdapter = new CategoryAdapter(categories, categoryName -> {
            Toast.makeText(getContext(), "Выбрана категория: " + categoryName, Toast.LENGTH_SHORT).show();

            // Открытие соответствующего фрагмента в зависимости от выбора
            Bundle bundle = new Bundle();
            switch (categoryName) {
                case "Логин":
                    navController.navigate(R.id.password); // переход к фрагменту паролей
                    break;
                case "Карта":
                    navController.navigate(R.id.card); // переход к фрагменту карт
                    break;
                case "Защищенная заметка":
                    navController.navigate(R.id.note); // переход к фрагменту заметок
                    break;
            }
        });

        recyclerCategories.setAdapter(categoryAdapter);

        // Логика кнопки добавления записи
        FloatingActionButton btnAddEntry = view.findViewById(R.id.btnAddEntry);
        btnAddEntry.setOnClickListener(v -> {
            BottomSheetAddEntryFragment bottomSheetFragment = new BottomSheetAddEntryFragment();
            bottomSheetFragment.show(getChildFragmentManager(), bottomSheetFragment.getTag());
        });

        return view;
    }
}

