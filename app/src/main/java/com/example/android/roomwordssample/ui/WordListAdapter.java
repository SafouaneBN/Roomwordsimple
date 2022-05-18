package com.example.android.roomwordssample.ui;



import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.roomwordssample.R;
import com.example.android.roomwordssample.entity.Word;
import com.example.android.roomwordssample.repository.WordRepository;

import java.util.List;


public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    static class WordViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;

        private WordViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<Word> mWords;
    private WordRepository wordRepository;

    WordListAdapter(Context context , WordRepository wordRepository) { mInflater = LayoutInflater.from(context);
    this.wordRepository =wordRepository;}

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        Word current = mWords.get(position);
        holder.wordItemView.setText(current.getWord());
        holder.itemView.setOnClickListener(view -> {
            wordRepository.delete(current);

        });
    }

    void setWords(List<Word> words){
        mWords = words;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        if (mWords != null)
            return mWords.size();
        else return 0;
    }
}


