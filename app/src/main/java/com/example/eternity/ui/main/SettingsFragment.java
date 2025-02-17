package com.example.eternity.ui.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eternity.R;


public class SettingsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        // Получаем доступ к Toolbar через NavController
        if (getActivity() != null) {
            AppCompatActivity activity = (AppCompatActivity) getActivity();
            if (activity.getSupportActionBar() != null) {
                // Показываем стрелку "назад" в ActionBar
                activity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                activity.getSupportActionBar().setDisplayShowHomeEnabled(false);
            }
        }

        return inflater.inflate(R.layout.fragment_settings, container, false);
    }
}