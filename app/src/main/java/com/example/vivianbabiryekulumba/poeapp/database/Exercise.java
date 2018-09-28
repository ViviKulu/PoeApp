package com.example.vivianbabiryekulumba.poeapp.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Exercise {

    @PrimaryKey(autoGenerate = true)
    private int exercise_id;

    @ColumnInfo(name = "exercise")
    private String exercise_direction;

    public int getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(int exercise_id) {
        this.exercise_id = exercise_id;
    }

    public String getExercise_direction() {
        return exercise_direction;
    }

    public void setExercise_direction(String exercise_direction) {
        this.exercise_direction = exercise_direction;
    }
}
