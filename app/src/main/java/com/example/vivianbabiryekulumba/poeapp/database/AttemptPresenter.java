package com.example.vivianbabiryekulumba.poeapp.database;


public class AttemptPresenter{
    AttemptDao attemptDao;
    ExerciseAttempt[] attempts = {};
    AttemptListPresentation presentation;

    public AttemptPresenter(AttemptDao attemptDao){
        this.attemptDao = attemptDao;
    }

    public void attach(AttemptListPresentation presentation){
        this.presentation = presentation;
    }

    public void detach(){
        presentation = null;
    }

    public void addAttempt(final String instruction, final String attempt){
        new Thread(new Runnable() {
            @Override
            public void run() {
                attemptDao.addAttempt(new ExerciseAttempt(instruction, attempt));
            }
        }).start();
    }

    public void onAttemptChanged(ExerciseAttempt[] exerciseAttempts){
        this.attempts = exerciseAttempts;

        if(presentation != null){
            presentation.notifyDataSetChanged();
        }
    }

    public int getItemCount() {
        return attempts.length;
    }

    public void bindView(AttemptListItem attemptListItem, int position) {
        attemptListItem.setInstruction(attempts[position].getInstruction());
        attemptListItem.setAttempt(attempts[position].getAttempt());

    }

    public interface AttemptListPresentation{
        void notifyDataSetChanged();
    }

    public interface AttemptListItem{
        void setAttempt(String attempt);
        void setInstruction(String instruction);
    }

}
