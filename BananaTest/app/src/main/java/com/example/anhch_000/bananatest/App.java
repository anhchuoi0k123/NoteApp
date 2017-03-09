package com.example.anhch_000.bananatest;

import android.app.Application;
import android.content.Context;

/**
 * Created by anhch_000 on 08/03/2017.
 */

public class App extends Application{
    private static Context context;

    @Override
    public void onCreate() {
        this.context = this;
        super.onCreate();

    }

    public static Context getContext() {
        return context;
    }
}
