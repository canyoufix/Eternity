package com.example.eternity.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eternity.R;
import com.example.eternity.models.PasswordModel;


public class PasswordAdapter extends ListAdapter<PasswordModel, PasswordAdapter.PasswordViewHolder> {

    public PasswordAdapter() {
        super(new DiffUtil.ItemCallback<PasswordModel>() {
            @Override
            public boolean areItemsTheSame(PasswordModel oldItem, PasswordModel newItem) {
                // Здесь проверяем, что элементы одинаковы по ID или другому уникальному признаку
                return oldItem.getId() == newItem.getId();  // Используем id или другой уникальный ключ
            }

            @Override
            public boolean areContentsTheSame(PasswordModel oldItem, PasswordModel newItem) {
                // Проверяем содержимое элементов
                return oldItem.getUsername().equals(newItem.getUsername()) &&
                        oldItem.getTitle().equals(newItem.getTitle());
            }
        });
    }

    @Override
    public PasswordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating нового item_password, который содержит CardView
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_password, parent, false);
        return new PasswordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PasswordViewHolder holder, int position) {
        PasswordModel password = getItem(position);  // Получаем элемент по позиции
        holder.bind(password);
    }

    public static class PasswordViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle, tvUsername;

        public PasswordViewHolder(View itemView) {
            super(itemView);
            // Находим элементы разметки внутри карточки
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvUsername = itemView.findViewById(R.id.tvUsername);
        }

        public void bind(PasswordModel password) {
            // Привязываем данные пароля к элементам
            tvTitle.setText(password.getTitle());
            tvUsername.setText(password.getUsername());
        }
    }
}





