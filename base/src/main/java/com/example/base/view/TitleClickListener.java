package com.example.base.view;

import android.app.Activity;

public abstract class TitleClickListener {
    private Activity mContent;

    public TitleClickListener(Activity mContent) {
        this.mContent = mContent;
    }

    public void blackClick() {
        mContent.finish();
    }

    public void right1Click() {
    }

    public void right2Click() {
    }
}