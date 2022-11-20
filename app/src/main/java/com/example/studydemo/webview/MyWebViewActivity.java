package com.example.studydemo.webview;

import android.view.View;

import com.example.studydemo.R;
import com.example.studydemo.base.BaseActivity;

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
