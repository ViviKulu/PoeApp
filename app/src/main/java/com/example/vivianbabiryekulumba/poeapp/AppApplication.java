package com.example.vivianbabiryekulumba.poeapp;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.vivianbabiryekulumba.poeapp.database.AttemptDatabase;

public class AppApplication extends Application {

    AttemptDatabase attemptDatabase;

    @Override
    public void onCreate() {
        super.onCreate();

        attemptDatabase = Room.databaseBuilder(this, AttemptDatabase.class, "exercise_attempt")
                .build();
    }

    public AttemptDatabase getAttemptDatabase() {
        return attemptDatabase;
    }
}
