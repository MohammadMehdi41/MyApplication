package com.example.admin.myapplication.view.custom;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import com.example.admin.myapplication.MyApplication;


public class CustomFontButton extends AppCompatButton {

    private  Context context;



    public CustomFontButton(Context context) {
        super(context);
        if (!isInEditMode()){
            setupButton();
        }
    }

    public CustomFontButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()){
            setupButton();
        }
    }

    public CustomFontButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!isInEditMode()){
            setupButton();
        }
    }


    private void setupButton() {
        MyApplication myApplication = (MyApplication) getContext().getApplicationContext();
        setTypeface(myApplication.getIranianSansFont());
    }
}
