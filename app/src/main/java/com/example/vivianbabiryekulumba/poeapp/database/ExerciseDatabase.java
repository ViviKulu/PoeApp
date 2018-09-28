package com.example.vivianbabiryekulumba.poeapp.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Exercise.class}, version = 1, exportSchema = false)
public abstract class ExerciseDatabase extends RoomDatabase {

    public abstract ExerciseDao exerciseDao();
}
