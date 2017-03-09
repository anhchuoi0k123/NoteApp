package com.example.anhch_000.bananatest.model;

import android.os.AsyncTask;
import android.util.Log;

import com.example.anhch_000.bananatest.interfaces.MainInteractorAction;
import com.example.anhch_000.bananatest.model.entity.Note;

import static android.content.ContentValues.TAG;

/**
 * Created by anhch_000 on 09/03/2017.
 */

public class MainInteractor implements MainInteractorAction {

    private DBManager dbManager;
    private OnTaskFinishListener onTaskFinishListener;
    private Note currentNote;

    public MainInteractor(DBManager dbManager, OnTaskFinishListener onTaskFinishListener) {

        this.dbManager = dbManager;
        this.onTaskFinishListener = onTaskFinishListener;
    }

    @Override
    public void getDatas() {
        class GetDatasTask extends AsyncTask<Void, Void, Boolean> {
            @Override
            protected void onPreExecute() {
                onTaskFinishListener.onStartTask();

                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                if (aBoolean) {
                    onTaskFinishListener.onFinishGetDatas();

                } else {
                    onTaskFinishListener.onGetDatasFail();
                }
                onTaskFinishListener.onFinishTask();
                super.onPostExecute(aBoolean);
            }

            @Override
            protected Boolean doInBackground(Void... params) {
                delay();

                return dbManager.getNoteListFromDatabase();
            }
        }
        new GetDatasTask().execute();

    }

    @Override
    public void deleteNote() {
        class DeleteNoteTask extends AsyncTask<Void, Void, Boolean> {
            @Override
            protected void onPreExecute() {
                onTaskFinishListener.onStartTask();

                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                if (aBoolean) {
                    onTaskFinishListener.onFinishDeleteNote();

                } else {
                    Log.d(TAG, "xoa fail on interactor");

                    onTaskFinishListener.onDeleteNoteFail();
                }
                onTaskFinishListener.onFinishTask();
                super.onPostExecute(aBoolean);
            }

            @Override
            protected Boolean doInBackground(Void... params) {
                delay();
                return dbManager.deleteNote(currentNote);
            }
        }
        new DeleteNoteTask().execute();
    }

    private void delay() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setCurrentNote(int position) {
        this.currentNote = dbManager.getNoteAt(position);
    }

    @Override
    public void addNote(final Note note) {
        class AddNoteTask extends AsyncTask<Void, Void, Boolean> {
            @Override
            protected void onPreExecute() {
                onTaskFinishListener.onStartTask();

                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                if (aBoolean) {
                    onTaskFinishListener.onFinishAddNote();
                    currentNote = note;
                } else {

                    onTaskFinishListener.onAddNoteFail();
                }
                onTaskFinishListener.onFinishTask();
                super.onPostExecute(aBoolean);
            }

            @Override
            protected Boolean doInBackground(Void... params) {
                delay();
                return dbManager.addNote(note);
            }
        }
        new AddNoteTask().execute();
    }

    public Note getNoteAt(int position) {
        return dbManager.getNoteAt(position);
    }


    public interface OnTaskFinishListener {
        void onFinishGetDatas();

        void onGetDatasFail();

        void onFinishDeleteNote();

        void onDeleteNoteFail();

        void onFinishTask();

        void onStartTask();

        void onFinishAddNote();

        void onAddNoteFail();
    }
}

