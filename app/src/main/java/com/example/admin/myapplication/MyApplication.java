package com.example.admin.myapplication;

import android.app.Application;
import android.graphics.Typeface;

public class MyApplication extends Application {
    private static Typeface iranianSansFont;
    @Override
    public void onCreate() {
        super.onCreate();
        iranianSansFont=Typeface.createFromAsset(getAssets(),"font/irannian_sans.ttf");
    }

    public static Typeface getIranianSansFont(){
        return iranianSansFont;
    }
}


