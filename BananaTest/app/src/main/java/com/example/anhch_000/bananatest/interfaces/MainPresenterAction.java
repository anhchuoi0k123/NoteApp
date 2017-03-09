package com.example.anhch_000.bananatest.interfaces;

import com.example.anhch_000.bananatest.model.entity.Note;

/**
 * Created by anhch_000 on 09/03/2017.
 */

public interface MainPresenterAction {
    void getDatas();

    void deleteNote();

    void addNote(Note note);
}
