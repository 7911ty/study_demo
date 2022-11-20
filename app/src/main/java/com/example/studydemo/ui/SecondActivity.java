package com.example.studydemo.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;

import com.example.studydemo.R;
import com.example.studydemo.base.BaseActivity;

public class SecondActivity extends BaseActivity {

    private View viewById;

    @Override
    protected int initLayout() {
        return R.layout.activity_second;
    }

    @Override
    protected void initView() {
        viewById = findViewById(R.id.sec_bt);
    }

    @Override
    protected void initData() {
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(SecondActivity.this).setPositiveButton("点击finlish当前页面", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SecondActivity.this.finish();
                    }
                }).show();
            }
        });
    }
}