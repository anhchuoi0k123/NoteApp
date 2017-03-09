package com.example.anhch_000.bananatest.util;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

import com.example.anhch_000.bananatest.App;

/**
 * Created by anhch_000 on 09/03/2017.
 */

public class Util {
    public static String getStringByid(int id) {
        return App.getContext().getResources().getString(id);
    }

    public static String convertColorToHex(Drawable drawable) {

        return String.format("#%06x",  (((ColorDrawable)drawable).getColor() & 0x00FFFFFF));
    }
}
