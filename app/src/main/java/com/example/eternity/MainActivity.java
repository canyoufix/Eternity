package com.example.eternity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentContainerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.navigation.ui.AppBarConfiguration;


import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private BottomNavigationView bottomNavigation;
    private NavController navController;
    private HashMap<Integer, String> titleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализация Toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Инициализация NavController
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();

        // Инициализация BottomNavigationView
        bottomNavigation = findViewById(R.id.bottom_navigation);
        NavigationUI.setupWithNavController(bottomNavigation, navController);

        // Инициализация карты заголовков
        initTitleMap();

        // Установка заголовка по умолчанию
        //getSupportActionBar().setTitle("Your Title");

        // Слушатель для изменения заголовка при переходе между фрагментами
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            updateToolbarTitle(destination.getId());
        });
    }

    private void initTitleMap() {
        titleMap = new HashMap<>();
        titleMap.put(R.id.storage, getString(R.string.storage));  // Заголовок для фрагмента storage
        titleMap.put(R.id.generator, getString(R.string.generator));  // Заголовок для фрагмента generator
        titleMap.put(R.id.settings, getString(R.string.settings));  // Заголовок для фрагмента settings
        // Добавьте сюда остальные ваши фрагменты, если нужно
    }

    private void updateToolbarTitle(int fragmentId) {
        String title = titleMap.get(fragmentId);
        if (title != null) {
            getSupportActionBar().setTitle(title); // Обновляем заголовок
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp();
    }
}




