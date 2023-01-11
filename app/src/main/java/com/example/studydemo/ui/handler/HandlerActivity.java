package com.example.studydemo.ui.handler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.base.ui.BaseActivity;
import com.example.studydemo.R;

public class HandlerActivity extends BaseActivity implements View.OnClickListener {

    private TextView tv_show;
    private Button bt_handler;
    private Handler handler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                tv_show.setText((String) msg.obj);
                Log.d(TAG, "what == 0 执行完毕");
                Toast.makeText(HandlerActivity.this,"what == 0 执行完毕",Toast.LENGTH_LONG).show();
            }
        }
    };

    @Override
    protected int initLayout() {
        return R.layout.activity_handler;
    }

    @Override
    protected void initView() {
        tv_show = findViewById(R.id.tv_show);
        bt_handler = findViewById(R.id.bt_handler);
        bt_handler.setOnClickListener(this);
    }

    @Override
    protected void initData() {
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.bt_handler) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String s = setText();
                    Message message = new Message();
                    message.what = 0;
                    message.obj = s;
                    handler.sendMessage(message);
                }
            }).start();
            Log.d(TAG, "onClick: button点击");
            Toast.makeText(HandlerActivity.this,"button点击",Toast.LENGTH_LONG).show();

        }
    }

    private String setText() {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < 100; i++) {
            buffer.append("字符串" + i);
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }
}