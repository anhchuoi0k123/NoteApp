package com.example.anhch_000.bananatest.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.anhch_000.bananatest.R;
import com.example.anhch_000.bananatest.model.entity.Note;
import com.example.anhch_000.bananatest.presenter.MainPresenter;

import java.util.ArrayList;

/**
 * Created by anhch_000 on 07/03/2017.
 */

public class AdapterNote extends BaseAdapter {
    private MainPresenter mainPresenter;
    private ArrayList<Note> notes;

    public AdapterNote(MainPresenter mainPresenter) {
        this.mainPresenter = mainPresenter;
    }

    @Override
    public int getCount() {
        if (notes == null) return 0;
        return notes.size();
    }

    @Override
    public Note getItem(int position) {
        return notes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Hodel hodel;
        if (convertView == null) {

            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
            hodel = new Hodel();

            hodel.lnItem = (LinearLayout) convertView.findViewById(R.id.ln_item);
            hodel.date = (TextView) convertView.findViewById(R.id.txt_date);
            hodel.content = (TextView) convertView.findViewById(R.id.tv_content);
            hodel.delete = (ImageView) convertView.findViewById(R.id.btn_delete);
            hodel.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()) {
                        case R.id.btn_delete:
                            mainPresenter.onClickDeleteAt(position);
                            break;
                        default:
                            break;
                    }
                }
            });

            convertView.setTag(hodel);
        } else {
            hodel = (Hodel) convertView.getTag();
        }

        hodel.lnItem.setBackgroundColor(Color.parseColor(notes.get(position).getColor()));
        hodel.date.setText(notes.get(position).getDate());
        hodel.content.setText(notes.get(position).getContent());
        hodel.date.setText(notes.get(position).getDate());

        return convertView;
    }

    public void setDatas(ArrayList<Note> noteList) {
        this.notes = noteList;
    }

    private class Hodel {
        LinearLayout lnItem;
        TextView date;
        TextView content;
        ImageView delete;
    }

}
