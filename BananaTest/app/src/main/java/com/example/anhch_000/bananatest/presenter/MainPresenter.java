package com.example.anhch_000.bananatest.presenter;

import com.example.anhch_000.bananatest.interfaces.MainPresenterAction;
import com.example.anhch_000.bananatest.model.DBManager;
import com.example.anhch_000.bananatest.model.MainInteractor;
import com.example.anhch_000.bananatest.model.entity.Note;

/**
 * Created by anhch_000 on 09/03/2017.
 */

public class MainPresenter implements MainPresenterAction, MainInteractor.OnTaskFinishListener {
    private MainView mainView;
    private MainInteractor mainInteractor;

    public MainPresenter(MainView mainView, DBManager dbManager) {

        mainInteractor = new MainInteractor(dbManager, this);
        this.mainView = mainView;
    }

    @Override
    public void onStartTask() {
        mainView.showProgress();
    }

    @Override
    public void onFinishTask() {
        mainView.hideProgress();
    }

    @Override
    public void getDatas() {
        mainInteractor.getDatas();

    }

    @Override
    public void onFinishGetDatas() {
        mainView.showContent();
    }

    @Override
    public void onGetDatasFail() {
        mainView.showMessageFail();
    }

    public void onClickDeleteAt(int position) {
        mainInteractor.setCurrentNote(position);
        mainView.showConfirmDialog();
    }


    @Override
    public void onFinishDeleteNote() {
        mainView.notifyDataChange();
        mainView.showMessageDeleteSucess();
    }

    @Override
    public void onDeleteNoteFail() {
        mainView.showMessageDeleteFail();

    }

    @Override
    public void deleteNote() {
        mainInteractor.deleteNote();
    }

    public void onClickAddNote() {
        mainView.showCreateNoteActivity();
    }

    @Override
    public void addNote(Note note) {
        mainInteractor.addNote(note);
    }


    @Override
    public void onFinishAddNote() {
        mainView.notifyDataChange();
        mainView.showMessageAddSuccess();
    }

    @Override
    public void onAddNoteFail() {
        mainView.showMessageAddFail();
    }

    public void onClickNote(int position) {
        mainView.showViewNoteDialog(mainInteractor.getNoteAt(position));
    }

    public interface MainView {
        void showProgress();

        void hideProgress();

        void showContent();

        void showMessageFail();

        void showMessageDeleteSucess();

        void showMessageDeleteFail();

        void showConfirmDialog();

        void showCreateNoteActivity();

        void showMessageAddSuccess();

        void showMessageAddFail();

        void notifyDataChange();

        void showViewNoteDialog(Note note);
    }

}
