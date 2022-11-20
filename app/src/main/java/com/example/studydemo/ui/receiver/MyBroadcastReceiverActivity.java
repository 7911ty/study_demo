package com.example.studydemo.ui.receiver;

import android.content.Intent;
import android.widget.Button;

import com.example.studydemo.R;
import com.example.studydemo.base.BaseActivity;

/**
 * 广播
 */
public class MyBroadcastReceiverActivity extends BaseActivity {
    @Override
    protected int initLayout() {
        return R.layout.activity_broadcast_receiver;
    }

    @Override
    protected void initView() {
        this.setShowTitle(true);
        Button broadcast_receiver_btn = findViewById(R.id.broadcast_receiver_btn);
        broadcast_receiver_btn.setOnClickListener(v -> {
            Intent intent = new Intent("com.example.myhw2.MY_RECEIVER");
            intent.setPackage(getPackageName());
            // 发送一个有序广播
            sendOrderedBroadcast(intent, null);
        });
    }

    @Override
    protected void initData() {
    }
}
