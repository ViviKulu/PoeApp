package com.example.vivianbabiryekulumba.poeapp.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "exercise_attempt")
public class ExerciseAttempt {


    @PrimaryKey(autoGenerate = true)
    private int attempt_id;

    @ColumnInfo(name = "instruction")
    public String instruction;

    @ColumnInfo(name = "attempt")
    public String attempt;

    public ExerciseAttempt(String instruction, String attempt) {
        this.instruction = instruction;
        this.attempt = attempt;
    }

    public int getAttempt_id() {
        return attempt_id;
    }

    public void setAttempt_id(int attempt_id) {
        this.attempt_id = attempt_id;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getAttempt() {
        return attempt;
    }

    public void setAttempt(String attempt) {
        this.attempt = attempt;
    }
}
