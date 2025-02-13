package com.example.eternity;


import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import android.view.View;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.Toolbar;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private BottomNavigationView bottomNavigation;
    private Toolbar toolbar;

    private Map<Integer, Integer> navigationMap;
    private Map<Integer, String> titleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.view_pager);
        viewPager.setUserInputEnabled(false);
        bottomNavigation = findViewById(R.id.bottom_navigation);

        // Список фрагментов
        List<Fragment> fragments = Arrays.asList(
                new Storage(),
                new Generator(),
                new Settings()
        );

        ViewPagerAdapter adapter = new ViewPagerAdapter(this, fragments);
        viewPager.setAdapter(adapter);

        // Карта ID навигации -> позиция ViewPager
        navigationMap = new HashMap<>();
        navigationMap.put(R.id.storage, 0);
        navigationMap.put(R.id.generator, 1);
        navigationMap.put(R.id.settings, 2);

        // Карта ID навигации -> заголовок Toolbar
        titleMap = new HashMap<>();
        titleMap.put(R.id.storage, getString(R.string.storage));
        titleMap.put(R.id.generator, getString(R.string.generator));
        titleMap.put(R.id.settings, getString(R.string.settings));

        // Устанавливаем заголовок по умолчанию
        toolbar.setTitle(titleMap.get(R.id.storage));

        // Обработчик нажатий BottomNavigation
        bottomNavigation.setOnItemSelectedListener(item -> {
            Integer position = navigationMap.get(item.getItemId());
            if (position != null) {
                viewPager.setCurrentItem(position, false);
                toolbar.setTitle(titleMap.get(item.getItemId())); // Устанавливаем заголовок
                return true;
            }
            return false;
        });

        // Синхронизация ViewPager2 и BottomNavigationView
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                int menuId = getMenuIdByPosition(position);
                bottomNavigation.setSelectedItemId(menuId);
                toolbar.setTitle(titleMap.get(menuId)); // Устанавливаем заголовок
            }
        });
    }

    // Метод для получения ID меню по позиции
    private int getMenuIdByPosition(int position) {
        for (Map.Entry<Integer, Integer> entry : navigationMap.entrySet()) {
            if (entry.getValue() == position) {
                return entry.getKey();
            }
        }
        return R.id.storage; // По умолчанию
    }
}

