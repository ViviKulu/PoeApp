package com.example.vivianbabiryekulumba.poeapp.database;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

public class AttemptObserver implements Observer<ExerciseAttempt[]> {

    private AttemptPresenter attemptPresenter;

    public AttemptObserver(AttemptPresenter attemptPresenter) {
        this.attemptPresenter = attemptPresenter;
    }

    @Override
    public void onChanged(@Nullable ExerciseAttempt[] exerciseAttempts) {
        attemptPresenter.onAttemptChanged(exerciseAttempts);
    }
}
