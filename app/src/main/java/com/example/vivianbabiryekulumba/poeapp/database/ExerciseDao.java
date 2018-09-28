package com.example.vivianbabiryekulumba.poeapp.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ExerciseDao {

    @Insert
    Long insertExercise(Exercise exercise);


    @Query("SELECT * FROM Exercise ORDER BY exercise_id desc")
    LiveData<List<Exercise>> fetchAllTasks();


    @Query("SELECT * FROM Exercise WHERE exercise_id =:exerciseId")
    LiveData<Exercise> getExercise(int exerciseId);


    @Update
    void updateExercise(Exercise exercise);


    @Delete
    void deleteExercise(Exercise exercise);
}
