package com.example.vivianbabiryekulumba.poeapp.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {ExerciseAttempt.class}, version = 1, exportSchema = false)
public abstract class AttemptDatabase extends RoomDatabase {
    public abstract AttemptDao attemptDao();
}
