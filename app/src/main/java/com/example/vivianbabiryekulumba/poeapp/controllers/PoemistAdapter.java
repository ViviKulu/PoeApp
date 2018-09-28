package com.example.vivianbabiryekulumba.poeapp.controllers;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vivianbabiryekulumba.poeapp.R;
import com.example.vivianbabiryekulumba.poeapp.models.Poem;

import java.util.List;

public class PoemistAdapter extends RecyclerView.Adapter<PoemistAdapter.PoemViewHolder> {

    private List<Poem> poemList;
    private static final String TAG = "PoemistAdapter";

    public PoemistAdapter(List<Poem> poemList) {
        this.poemList = poemList;
    }

    @NonNull
    @Override
    public PoemistAdapter.PoemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.poem_item_view, parent, false);
        return new PoemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PoemistAdapter.PoemViewHolder poemViewHolder, int position) {
        Poem poem = poemList.get(position);
        poemViewHolder.poemTitle.setText(poem.getTitle());
        poemViewHolder.poetName.setText("By: " + poem.getPoet().getName());
        poemViewHolder.poemContent.setText(poem.getContent());
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: " + poemList.size());
        return poemList.size();
    }

    public class PoemViewHolder extends RecyclerView.ViewHolder {

        TextView poemTitle;
        TextView poetName;
        TextView poemContent;

        public PoemViewHolder(@NonNull View itemView) {
            super(itemView);

            poemTitle = itemView.findViewById(R.id.poem_title_tv);
            poetName = itemView.findViewById(R.id.poet_name_tv);
            poemContent = itemView.findViewById(R.id.poem_content_tv);
        }
    }
}
