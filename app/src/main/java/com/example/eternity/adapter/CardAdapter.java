package com.example.eternity.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eternity.R;
import com.example.eternity.models.CardModel;

public class CardAdapter extends ListAdapter<CardModel, CardAdapter.CardViewHolder> {

    // Конструктор адаптера
    public CardAdapter() {
        super(new DiffUtil.ItemCallback<CardModel>() {
            @Override
            public boolean areItemsTheSame(CardModel oldItem, CardModel newItem) {
                return oldItem.getId() == newItem.getId();  // Сравниваем по ID
            }

            @Override
            public boolean areContentsTheSame(CardModel oldItem, CardModel newItem) {
                return oldItem.equals(newItem);  // Сравниваем весь объект
            }
        });
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        CardModel card = getItem(position);  // Получаем элемент по позиции
        holder.bind(card);
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle, tvCardNumber;

        public CardViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvCardNumber = itemView.findViewById(R.id.tvCardNumber);  // Текст для дополнительных данных
        }

        public void bind(CardModel card) {
            tvTitle.setText(card.getTitle());

            // Отображаем последние 4 цифры номера карты
            String last4Digits = card.getCardNumber().substring(card.getCardNumber().length() - 4);
            tvCardNumber.setText("*" + last4Digits);
        }
    }
}

