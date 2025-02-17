package com.example.eternity.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eternity.R;
import com.example.eternity.models.NoteModel;

public class NoteAdapter extends ListAdapter<NoteModel, NoteAdapter.NoteViewHolder> {

    // Конструктор адаптера
    public NoteAdapter() {
        super(new DiffUtil.ItemCallback<NoteModel>() {
            @Override
            public boolean areItemsTheSame(NoteModel oldItem, NoteModel newItem) {
                return oldItem.getId() == newItem.getId();  // Сравниваем по ID
            }

            @Override
            public boolean areContentsTheSame(NoteModel oldItem, NoteModel newItem) {
                return oldItem.equals(newItem);  // Сравниваем весь объект
            }
        });
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        NoteModel note = getItem(position);  // Получаем элемент по позиции
        holder.bind(note);
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;

        public NoteViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
        }

        public void bind(NoteModel note) {
            tvTitle.setText(note.getTitle());
        }
    }
}
