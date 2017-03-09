package com.example.anhch_000.bananatest.view.dialog;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.anhch_000.bananatest.R;
import com.example.anhch_000.bananatest.presenter.MainPresenter;
import com.example.anhch_000.bananatest.util.Util;

/**
 * Created by anhch_000 on 08/03/2017.
 */

public class ConfirmDialog extends BaseDialog {
    private MainPresenter mainPresenter;

    public ConfirmDialog(Context context, MainPresenter mainPresenter) {
        super(context);
        this.mainPresenter = mainPresenter;
        setContentView(R.layout.confirm_dialog);

        tvContent = (TextView) findViewById(R.id.tv_content);
        tvTitle = (TextView) findViewById(R.id.tv_title);

        tvContent.setText(Util.getStringByid(R.string.ban_co_muon_xoa));
        tvTitle.setText(Util.getStringByid(R.string.xac_nhan));

        btnCancel = (Button) findViewById(R.id.btn_close);
        btnOk = (Button) findViewById(R.id.btn_ok);

        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.btn_ok) {
            mainPresenter.deleteNote();
            dismiss();
        }
    }
}
