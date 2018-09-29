package com.example.vivianbabiryekulumba.poeapp.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

@Dao
public interface AttemptDao {

    @Insert()
    Long addAttempt(ExerciseAttempt exerciseAttempt);

    @Query("DELETE FROM exercise_attempt")
    void deleteAll();

    @Query("SELECT * from exercise_attempt ORDER BY attempt_id ASC")
    LiveData<ExerciseAttempt[]> getAllAttempts();

}
