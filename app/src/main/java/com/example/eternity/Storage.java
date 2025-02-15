package com.example.eternity;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.eternity.ui.BottomSheetAddEntryFragment;



public class Storage extends Fragment {

    public Storage() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_storage, container, false);

        // Находим кнопку и устанавливаем обработчик
        FloatingActionButton btnAddEntry = view.findViewById(R.id.btnAddEntry);
        btnAddEntry.setOnClickListener(v -> {
            // Открываем BottomSheet через фрагмент
            BottomSheetAddEntryFragment bottomSheetFragment = new BottomSheetAddEntryFragment();
            bottomSheetFragment.show(getChildFragmentManager(), bottomSheetFragment.getTag());
        });

        return view;
    }
}

