package com.example.studydemo.ui.receiver;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.studydemo.R;
import com.example.studydemo.base.BaseActivity;

public class MyBroadcastReceiverActivity extends BaseActivity {
    @Override
    protected int initLayout() {
        return R.layout.activity_broadcast_receiver;
    }

    @Override
    protected void initView() {
        Button broadcast_receiver_btn = findViewById(R.id.broadcast_receiver_btn);
        broadcast_receiver_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.myhw2.MY_RECEIVER");
                intent.setPackage(getPackageName());
                sendOrderedBroadcast(intent,null);
            }
        });
    }

    @Override
    protected void initData() {

    }
}
