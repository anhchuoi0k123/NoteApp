package com.example.anhch_000.bananatest.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.anhch_000.bananatest.R;
import com.example.anhch_000.bananatest.adapter.AdapterNote;
import com.example.anhch_000.bananatest.model.DBManager;
import com.example.anhch_000.bananatest.model.entity.Note;
import com.example.anhch_000.bananatest.presenter.MainPresenter;
import com.example.anhch_000.bananatest.util.Util;
import com.example.anhch_000.bananatest.view.dialog.ConfirmDialog;
import com.example.anhch_000.bananatest.view.dialog.ViewNoteDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MainPresenter.MainView {

    private static final int ACTIVITY_CREATE_NOTE = 1996;
    private ListView listView;
    private AdapterNote adapterNote;
    private DBManager dbManager;
    private ConfirmDialog confirmDialog;
    private ViewNoteDialog viewNoteDialog;
    private ImageView btnAdd;
    private MainPresenter mainPresenter;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_note);
        findViews();
        initComponets();
        setEvents();
        mainPresenter.getDatas();
    }

    private void initComponets() {
        dbManager = new DBManager();
        mainPresenter = new MainPresenter(this, dbManager);

        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(Util.getStringByid(R.string.doi_teo));

        viewNoteDialog = new ViewNoteDialog(this);
        confirmDialog = new ConfirmDialog(this, mainPresenter);
        adapterNote = new AdapterNote(mainPresenter);

    }

    @Override
    protected void onDestroy() {
        if (progressDialog != null) {
            progressDialog.cancel();
        }
        if (confirmDialog != null) {
            confirmDialog.cancel();
        }
        super.onDestroy();
    }

    @Override
    public void showMessageFail() {
        showToast(Util.getStringByid(R.string.tai_du_lieu_that_bai));
    }

    private void setEvents() {
        btnAdd.setOnClickListener(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mainPresenter.onClickNote(position);
            }
        });

    }

    private void findViews() {
        btnAdd = (ImageView) findViewById(R.id.imv_add);
        listView = (ListView) findViewById(R.id.list_view);
    }

    @Override
    public void showViewNoteDialog(Note note) {
        viewNoteDialog.setContent(note);
        viewNoteDialog.show();
    }

    @Override
    public void showContent() {
        adapterNote.setDatas(dbManager.getNoteList());
        listView.setAdapter(adapterNote);

    }

    @Override
    public void showMessageDeleteSucess() {
        showToast(Util.getStringByid(R.string.xoa_thanh_cong));
    }

    @Override
    public void showMessageDeleteFail() {
        showToast(Util.getStringByid(R.string.xoa_that_bai));
    }

    @Override
    public void showConfirmDialog() {
        confirmDialog.show();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imv_add) {
            mainPresenter.onClickAddNote();
        }
    }

    @Override
    public void showCreateNoteActivity() {
        startActivityForResult(new Intent(this, CreateNoteActivity.class), ACTIVITY_CREATE_NOTE);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ACTIVITY_CREATE_NOTE) {
            if (resultCode == CreateNoteActivity.RESULT_OK) {

                Note note = (Note) data.getSerializableExtra(CreateNoteActivity.NOTE_DATA);

                mainPresenter.addNote(note);

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void notifyDataChange() {
        adapterNote.notifyDataSetChanged();
    }

    @Override
    public void showMessageAddSuccess() {
        listView.smoothScrollToPosition(0);
        showToast(Util.getStringByid(R.string.them_moi_thanh_cong));
    }

    @Override
    public void showMessageAddFail() {
        showToast(Util.getStringByid(R.string.them_moi_that_bai));
    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }
}
