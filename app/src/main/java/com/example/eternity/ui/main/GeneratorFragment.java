package com.example.eternity.ui.main;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.eternity.R;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.security.SecureRandom;

public class GeneratorFragment extends Fragment {
    // Строки с возможными символами
    private static final String SMALL_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String CAPITAL_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_=+";

    private static final SecureRandom secureRandom = new SecureRandom();

    private int passwordLength = 16;
    private boolean useSpecialChars = true;
    private boolean useCapitalLetters = true;
    private boolean useSmallLetters = true;
    private boolean useNumbers = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_generator, container, false);

        // Получаем доступ к Toolbar через NavController
        if (getActivity() != null) {
            AppCompatActivity activity = (AppCompatActivity) getActivity();
            if (activity.getSupportActionBar() != null) {
                // Показываем стрелку "назад" в ActionBar
                activity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                activity.getSupportActionBar().setDisplayShowHomeEnabled(false);
            }
        }

        Button generateButton = view.findViewById(R.id.btnGenerate);
        ImageButton copyButton = view.findViewById(R.id.btnCopy);

        TextView passwordTextView = view.findViewById(R.id.passwordTextView);
        TextView passwordLengthLabel = view.findViewById(R.id.passwordLengthLabel);

        SeekBar passwordLengthSeekBar = view.findViewById(R.id.passwordLengthSeekBar);

        // Установка начального значения SeekBar на 16
        passwordLengthSeekBar.setProgress(16);
        passwordLengthLabel.setText(getString(R.string.length) +"\n16");

        SwitchMaterial specialCharsSwitch = view.findViewById(R.id.specialCharsSwitch);
        SwitchMaterial capitalLettersSwitch = view.findViewById(R.id.capitalLettersSwitch);
        SwitchMaterial smallLettersSwitch = view.findViewById(R.id.smallLettersSwitch);
        SwitchMaterial numbersSwitch = view.findViewById(R.id.numbersSwitch);


        // Инициализация свитчей
        specialCharsSwitch.setChecked(true); // Включаем спецсимволы
        capitalLettersSwitch.setChecked(true); // Включаем заглавные буквы
        smallLettersSwitch.setChecked(true); // Включаем маленькие буквы
        numbersSwitch.setChecked(true); // Включаем цифры


        // Восстанавливаем значения ползунков
        loadPreferences(passwordLengthSeekBar, specialCharsSwitch, capitalLettersSwitch, smallLettersSwitch, numbersSwitch, passwordLengthLabel);

        // Обработчик изменения длины пароля через SeekBar
        passwordLengthSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                passwordLength = Math.max(progress, 8); // Минимальная длина 8
                passwordLengthLabel.setText(getString(R.string.length) + "\n" + passwordLength);
                savePreferences(passwordLength);
                String newPassword = generatePassword(
                        passwordLength,
                        useSpecialChars,
                        useCapitalLetters,
                        useSmallLetters,
                        useNumbers
                );
                passwordTextView.setText(newPassword);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });


        // Проверка, если все свитчи выключены
        View.OnClickListener switchListener = v -> {
            // Проверяем, если все свитчи выключены
            if (!specialCharsSwitch.isChecked() && !capitalLettersSwitch.isChecked() &&
                    !smallLettersSwitch.isChecked() && !numbersSwitch.isChecked()) {

                // Включаем маленькие буквы и блокируем его
                smallLettersSwitch.setChecked(true);
                smallLettersSwitch.setEnabled(false); // Блокируем свитч
            } else {
                smallLettersSwitch.setEnabled(true); // Разблокируем свитч
            }

            // Сохраняем настройки при изменении состояния свитчей
            savePreferences(passwordLength);
        };

        // Добавляем обработчики для свитчей
        specialCharsSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            useSpecialChars = isChecked;
            switchListener.onClick(buttonView);
            savePreferences(passwordLength);
        });
        capitalLettersSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            useCapitalLetters = isChecked;
            switchListener.onClick(buttonView);
            savePreferences(passwordLength);
        });
        smallLettersSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            useSmallLetters = isChecked;
            switchListener.onClick(buttonView);
            savePreferences(passwordLength);
        });
        numbersSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            useNumbers = isChecked;
            switchListener.onClick(buttonView);
            savePreferences(passwordLength);
        });

        // Обработчик кнопки генерации пароля
        generateButton.setOnClickListener(v -> {
            String newPassword = generatePassword(
                    passwordLength,
                    useSpecialChars,
                    useCapitalLetters,
                    useSmallLetters,
                    useNumbers
            );
            passwordTextView.setText(newPassword);
        });

        copyButton.setOnClickListener(v -> {
            // Получаем текущий текст из TextView (пароль, который был сгенерирован)
            String passwordToCopy = passwordTextView.getText().toString();
            if (!passwordToCopy.isEmpty()) {
                // Копируем в буфер обмена
                copyToClipboard(passwordToCopy);
            } else {
                Toast.makeText(requireContext(), "Пароль ещё не сгенерирован!", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    // Метод генерации пароля
    private String generatePassword(int length, boolean useSpecial, boolean useCapitalLetters, boolean useSmallLetters, boolean useNumbers) {
        StringBuilder characters = new StringBuilder();

        // Добавляем символы в зависимости от состояния переключателей
        if (useCapitalLetters) {
            characters.append(CAPITAL_LETTERS);
        }
        if (useSmallLetters) {
            characters.append(SMALL_LETTERS);
        }
        if (useNumbers) {
            characters.append(DIGITS);
        }
        if (useSpecial) {
            characters.append(SPECIAL_CHARACTERS);
        }

        // Если ни один переключатель не включен, добавляем хотя бы маленькие и заглавные буквы
        if (characters.length() == 0) {
            characters.append(SMALL_LETTERS).append(CAPITAL_LETTERS);
        }

        // Генерация пароля
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            password.append(characters.charAt(secureRandom.nextInt(characters.length())));
        }

        return password.toString();
    }

    // Метод копирования в буфер обмена
    private void copyToClipboard(String text) {
        ClipboardManager clipboard = (ClipboardManager) requireContext().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Password", text);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(requireContext(), "Пароль скопирован!", Toast.LENGTH_SHORT).show();
    }

    private void savePreferences(int passwordLength) {
        SharedPreferences preferences = requireContext().getSharedPreferences("PasswordPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        // Сохраняем состояние свитчей
        editor.putBoolean("useSpecialChars", useSpecialChars);
        editor.putBoolean("useCapitalLetters", useCapitalLetters);
        editor.putBoolean("useSmallLetters", useSmallLetters);
        editor.putBoolean("useNumbers", useNumbers);

        // Сохраняем значение ползунка
        editor.putInt("passwordLength", passwordLength);

        editor.apply();
    }

    private void loadPreferences(SeekBar passwordLengthSeekBar, SwitchMaterial specialCharsSwitch, SwitchMaterial capitalLettersSwitch, SwitchMaterial smallLettersSwitch, SwitchMaterial numbersSwitch, TextView passwordLengthLabel) {
        SharedPreferences preferences = requireContext().getSharedPreferences("PasswordPreferences", Context.MODE_PRIVATE);

        // Восстанавливаем настройки свитчей
        useSpecialChars = preferences.getBoolean("useSpecialChars", true);
        useCapitalLetters = preferences.getBoolean("useCapitalLetters", true);
        useSmallLetters = preferences.getBoolean("useSmallLetters", true);
        useNumbers = preferences.getBoolean("useNumbers", true);

        specialCharsSwitch.setChecked(useSpecialChars);
        capitalLettersSwitch.setChecked(useCapitalLetters);
        smallLettersSwitch.setChecked(useSmallLetters);
        numbersSwitch.setChecked(useNumbers);


        // Восстанавливаем значение ползунка
        passwordLength = preferences.getInt("passwordLength", 16); // 16 — значение по умолчанию
        passwordLengthSeekBar.setProgress(passwordLength);
        passwordLengthLabel.setText(getString(R.string.length) + "\n" + passwordLength);
    }


}
