package com.example.vivianbabiryekulumba.poeapp.controllers;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vivianbabiryekulumba.poeapp.R;
import com.example.vivianbabiryekulumba.poeapp.database.AttemptPresenter;

public class AttemptListAdapter extends RecyclerView.Adapter<AttemptListAdapter.AttemptListViewHolder> {

    private static final String TAG = "AttemptListAdapter";
    private AttemptPresenter attemptPresenter;

    public AttemptListAdapter(AttemptPresenter attemptPresenter) {
        this.attemptPresenter = attemptPresenter;
    }

    @NonNull
    @Override
    public AttemptListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.attempt_list_item_view, parent, false);
        return new AttemptListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AttemptListViewHolder attemptListViewHolder, int position) {
        attemptPresenter.bindView(attemptListViewHolder, position);
        Log.d(TAG, "onBindViewHolder: " + attemptPresenter.getItemCount());
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: " + attemptPresenter.getItemCount());
        return attemptPresenter.getItemCount();
    }

    public class AttemptListViewHolder extends RecyclerView.ViewHolder implements AttemptPresenter.AttemptListItem{

        TextView attempt_exercise_instruction;
        TextView attempt_exercise_content;

        public AttemptListViewHolder(@NonNull View itemView) {
            super(itemView);

            attempt_exercise_instruction = itemView.findViewById(R.id.attempt_writing_exercise_tv);
            attempt_exercise_content = itemView.findViewById(R.id.exercise_attempt_tv);
        }

        @Override
        public void setAttempt(String attempt) {
            attempt_exercise_content.setText(attempt);
        }

        @Override
        public void setInstruction(String instruction) {
            attempt_exercise_instruction.setText(instruction);
        }
    }
}
