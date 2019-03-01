package com.example.admin.myapplication.view.custom;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.example.admin.myapplication.MyApplication;

public class CustomFontTextView extends AppCompatTextView {
    private Context context;
    public CustomFontTextView(Context context) {
        super(context);
        if (!isInEditMode()) {
            setupTextView();
        }
    }

    public CustomFontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            setupTextView();
        }
    }

    public CustomFontTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!isInEditMode()) {
            setupTextView();
        }
    }


    private void setupTextView() {
        MyApplication myApplication = (MyApplication) getContext().getApplicationContext();
        setTypeface(myApplication.getIranianSansFont());
    }
}
