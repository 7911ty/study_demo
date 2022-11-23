package com.example.studydemo.ui.eventbus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.studydemo.R;
import com.example.studydemo.base.BaseActivity;
import com.example.studydemo.utils.StatusBarUtil;

public class IntentMsgActivity2 extends BaseActivity {

    private Button button;
    private EditText editText;

    @Override
    protected int initLayout() {
        return R.layout.activity_eventbus_publish;
    }

    @Override
    protected void initView() {
        // 处理通知栏
        StatusBarUtil.setTranslucentStatus(this);
        StatusBarUtil.setAndroidNativeLightStatusBar(this, false);
        button = findViewById(R.id.set_btn);
        editText = findViewById(R.id.edit_text);
    }

    @Override
    protected void initData() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                Bundle bundle = new Bundle();
                bundle.putString("name", "fuxinzi");
                bundle.putString("password", "85056257");
                intent.putExtras(bundle);
                setResult(0x12, intent);
                finish();
            }
        });
    }}
