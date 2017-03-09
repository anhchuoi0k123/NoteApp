package com.example.anhch_000.bananatest.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.anhch_000.bananatest.R;
import com.example.anhch_000.bananatest.model.entity.Note;
import com.example.anhch_000.bananatest.util.Util;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by anhch_000 on 08/03/2017.
 */

public class CreateNoteActivity extends Activity implements View.OnClickListener {
    public static final int RESULT_OK = 1;
    public static final int RESULT_NULL = 0;
    public static final String NOTE_DATA = "NOTE_DATA";

    private EditText edtContenNote;
    private DatePicker datePicker;
    private View[] colorPick;
    private Button btnCreateNote;
    private HashMap<Integer, String> hashMapStringHexColor;
    private int[] ids;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creat_note);
        findViews();
        initComponents();
        setEvents();
    }

    private void initComponents() {

        ids = new int[]{R.id.note_1, R.id.note_2, R.id.note_3, R.id.note_4, R.id.note_5};

        hashMapStringHexColor = new HashMap<>();
        hashMapStringHexColor.put(ids[0], "#006fff");
        hashMapStringHexColor.put(ids[1], "#ecac57");
        hashMapStringHexColor.put(ids[2], "#df2828");
        hashMapStringHexColor.put(ids[3], "#16c216");
        hashMapStringHexColor.put(ids[4], "#ac1fe3");

        colorPick = new View[hashMapStringHexColor.size()];
        int i = 0;
        for (View view : colorPick) {
            view = findViewById(getIDColorPick(i));
            i++;
            view.setOnClickListener(this);
        }
    }

    private void setEvents() {
        btnCreateNote.setOnClickListener(this);
        datePicker.requestFocus();
    }

    private int getIDColorPick(int i) {
        return ids[i];
    }

    private void findViews() {
        edtContenNote = (EditText) findViewById(R.id.edt_conten_note);
        datePicker = (DatePicker) findViewById(R.id.date_picker);
        btnCreateNote = (Button) findViewById(R.id.btn_create_note);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_create_note) {
            if (TextUtils.isEmpty(edtContenNote.getText())) {
                edtContenNote.setError(Util.getStringByid(R.string.nhap_ghi_nho));
                edtContenNote.requestFocus();
                return;
            }

            Note note = new Note();
            note.setContent(edtContenNote.getText().toString().trim());
            note.setDate(datePicker.getDayOfMonth() + "/" + (datePicker.getMonth() + 1) + "/" + datePicker.getYear());
            note.setColor(Util.convertColorToHex(edtContenNote.getBackground()));

            Intent intent = new Intent();

            intent.putExtra(NOTE_DATA, note);

            setResult(RESULT_OK, intent);
            finish();
        } else {
            if (!v.isSelected()) {
                setColorView(v.getId());
            }
        }
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_NULL);
        super.onBackPressed();
    }

    private void setColorView(int id) {
        edtContenNote.setBackgroundColor(Color.parseColor(hashMapStringHexColor.get(id)));

    }
}
