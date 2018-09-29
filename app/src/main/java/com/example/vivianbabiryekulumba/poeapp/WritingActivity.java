package com.example.vivianbabiryekulumba.poeapp;

import android.animation.Animator;
import android.arch.lifecycle.LiveData;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vivianbabiryekulumba.poeapp.database.AttemptDao;
import com.example.vivianbabiryekulumba.poeapp.database.AttemptDatabase;
import com.example.vivianbabiryekulumba.poeapp.database.AttemptObserver;
import com.example.vivianbabiryekulumba.poeapp.database.AttemptPresenter;
import com.example.vivianbabiryekulumba.poeapp.database.ExerciseAttempt;

import java.util.Random;

public class WritingActivity extends AppCompatActivity {

    private static final String TAG = "WritingActivity";
    FloatingActionButton fab, fab1, fab2;
    LinearLayout fabLayout1, fabLayout2;
    View fabBGLayout;
    boolean isFABOpen=false;
    TextView writing_exercise;
    TextInputEditText exercise_attempt;
    Button save;
    AttemptPresenter attemptPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing);

        fabLayout1= findViewById(R.id.fabLayout1);
        fabLayout2= findViewById(R.id.fabLayout2);
        fab = findViewById(R.id.fab);
        fab1 = findViewById(R.id.fab1);
        fab2= findViewById(R.id.fab2);
        fabBGLayout=findViewById(R.id.fabBGLayout);
        save = findViewById(R.id.save_button);

        writing_exercise = findViewById(R.id.writing_exercise_tv);
        exercise_attempt = findViewById(R.id.exercise_attempt_et);

        String[] writing_exercises = {"Stop and take a moment to close your eyes. Think of what you hear and see when your eyes are closed. Open your eyes and write what you heard and saw when your eyes were closed yet you heard sound around you.",
                "Write a letter to your younger self", "Write a story that was once told to you.", "Which of your parents are you more like? How do you feel about that?"};

        Random random = new Random();
        for(int i = 0; i < writing_exercises.length; i++){
            writing_exercise.setText(writing_exercises[random.nextInt(4)]);
        }

        AttemptDatabase attemptDatabase = ((AppApplication) getApplication()).getAttemptDatabase();
        AttemptDao attemptDao = attemptDatabase.attemptDao();

        attemptPresenter = new AttemptPresenter(attemptDao);
        LiveData<ExerciseAttempt[]> attempts = attemptDao.getAllAttempts();
        attempts.observe(this, new AttemptObserver(attemptPresenter));

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptPresenter.addAttempt(writing_exercise.getText().toString(), exercise_attempt.getText().toString());
                Toast.makeText(getApplicationContext(), "Saved your work! Check out submissions tab!", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onClick: writing activity" + writing_exercise.getText().toString() + exercise_attempt.getText().toString());
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isFABOpen){
                    showFABMenu();
                }else{
                    closeFABMenu();
                }
            }
        });

        fabBGLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeFABMenu();
            }
        });

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WritingActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WritingActivity.this, AttemptListActivity.class);
                startActivity(intent);
            }
        });



    }

    private void showFABMenu(){
        isFABOpen=true;
        fabLayout1.setVisibility(View.VISIBLE);
        fabLayout2.setVisibility(View.VISIBLE);
        fabBGLayout.setVisibility(View.VISIBLE);

        fab.animate().rotationBy(180);
        fabLayout1.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
        fabLayout2.animate().translationY(-getResources().getDimension(R.dimen.standard_100));
    }

    private void closeFABMenu(){
        isFABOpen=false;
        fabBGLayout.setVisibility(View.GONE);
        fab.animate().rotationBy(-180);
        fabLayout1.animate().translationY(0);
        fabLayout2.animate().translationY(0).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if(!isFABOpen){
                    fabLayout1.setVisibility(View.GONE);
                    fabLayout2.setVisibility(View.GONE);
                }

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        if(isFABOpen){
            closeFABMenu();
        }else{
            super.onBackPressed();
        }
    }
}
