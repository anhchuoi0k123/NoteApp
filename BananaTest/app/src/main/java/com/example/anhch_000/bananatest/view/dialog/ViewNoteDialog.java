package com.example.anhch_000.bananatest.view.dialog;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.anhch_000.bananatest.R;
import com.example.anhch_000.bananatest.model.entity.Note;

/**
 * Created by anhch_000 on 09/03/2017.
 */

public class ViewNoteDialog extends BaseDialog {

    private LinearLayout lnItem;
    public ViewNoteDialog(Context context) {
        super(context);
        setContentView(R.layout.view_note_dialog);

        lnItem = (LinearLayout) findViewById(R.id.ln_item);

        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvContent = (TextView) findViewById(R.id.tv_content);

        btnCancel = (Button) findViewById(R.id.btn_close);

        btnCancel.setOnClickListener(this);
    }

    public void setContent(Note note) {
        tvTitle.setText(note.getDate());
        tvContent.setText(note.getContent());
        lnItem.setBackgroundColor(Color.parseColor(note.getColor()));

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }
}
