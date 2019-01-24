package br.com.app.burg.burger.utils;

import android.app.Application;
import android.content.Context;



public class MyApplication extends Application {
    private String TOKEN;
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();

    }

    public static Context getContext() {
        return mContext;
    }

}
