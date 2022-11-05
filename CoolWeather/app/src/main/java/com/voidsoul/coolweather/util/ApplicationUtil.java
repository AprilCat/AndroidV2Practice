package com.voidsoul.coolweather.util;

import android.app.Application;
import android.content.Context;

import org.litepal.LitePal;

public class ApplicationUtil extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        LitePal.initialize(context);
    }

    public static Context getContext(){
        return context;
    }
}
