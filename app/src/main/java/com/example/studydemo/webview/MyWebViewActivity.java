package com.example.studydemo.webview;

import android.view.View;

import com.example.base.ui.BaseActivity;
import com.example.studydemo.R;

public class MyWebViewActivity  extends BaseActivity {
    @Override
    protected int initLayout() {
        return R.layout.activity_webview;
    }

    @Override
    protected void initView() {
        View viewById = findViewById(R.id.webview);
    }

    @Override
    protected void initData() {

    }
}
