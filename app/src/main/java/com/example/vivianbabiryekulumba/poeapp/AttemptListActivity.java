package com.example.vivianbabiryekulumba.poeapp;

import android.animation.Animator;
import android.arch.lifecycle.LiveData;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import com.example.vivianbabiryekulumba.poeapp.controllers.AttemptListAdapter;
import com.example.vivianbabiryekulumba.poeapp.database.AttemptDao;
import com.example.vivianbabiryekulumba.poeapp.database.AttemptDatabase;
import com.example.vivianbabiryekulumba.poeapp.database.AttemptObserver;
import com.example.vivianbabiryekulumba.poeapp.database.AttemptPresenter;
import com.example.vivianbabiryekulumba.poeapp.database.ExerciseAttempt;

public class AttemptListActivity extends AppCompatActivity implements AttemptPresenter.AttemptListPresentation{

    private static final String TAG = "AttemptList";
    FloatingActionButton fab, fab1, fab2;
    LinearLayout fabLayout1, fabLayout2;
    View fabBGLayout;
    boolean isFABOpen=false;
    RecyclerView recyclerView;
    AttemptPresenter attemptPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attempt_list);

        fabLayout1= findViewById(R.id.fabLayout1);
        fabLayout2= findViewById(R.id.fabLayout2);
        fab = findViewById(R.id.fab);
        fab1 = findViewById(R.id.fab1);
        fab2= findViewById(R.id.fab2);
        fabBGLayout=findViewById(R.id.fabBGLayout);
        recyclerView = findViewById(R.id.attempt_recycler);

        AttemptDatabase db = ((AppApplication) getApplication()).getAttemptDatabase();
        AttemptDao attemptDao = db.attemptDao();

        attemptPresenter = new AttemptPresenter(attemptDao);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        recyclerView.setAdapter(new AttemptListAdapter(attemptPresenter));
        Log.d(TAG, "onCreate: " + attemptPresenter.getItemCount());

        LiveData<ExerciseAttempt[]> attempts = attemptDao.getAllAttempts();
        attempts.observe(this, new AttemptObserver(attemptPresenter));

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
                Intent intent = new Intent(AttemptListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AttemptListActivity.this, WritingActivity.class);
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

    @Override protected void onStart() {
        super.onStart();
        attemptPresenter.attach(this);
    }

    @Override protected void onStop() {
        super.onStop();
        attemptPresenter.detach();
    }

    @Override
    public void onBackPressed() {
        if(isFABOpen){
            closeFABMenu();
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public void notifyDataSetChanged() {
        recyclerView.getAdapter().notifyDataSetChanged();
    }
}
