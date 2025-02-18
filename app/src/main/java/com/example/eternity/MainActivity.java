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
    private BottomNavigationView bottomNavigationView;
    private NavController navController;
    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Находим BottomNavigation
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Находим NavHostFragment, который будет содержать навигацию
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();

        // Настройка BottomNavigation для работы с NavController
        NavigationUI.setupWithNavController(bottomNavigationView, navController);


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Настройка действия с Toolbar (если используете)
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.storageFragment, R.id.generatorFragment, R.id.settingsFragment)
                .build();

        // Настройка ActionBar для отображения заголовков фрагментов
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}





