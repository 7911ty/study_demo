package com.example.studydemo.ui.eventbus;

import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.base.ui.BaseActivity;
import com.example.base.utils.StatusBarUtil;
import com.example.studydemo.R;
import com.example.studydemo.bean.MessageWrap;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EventBusActivity1 extends BaseActivity {

    private EditText edit_get;

    @Override
    protected int initLayout() {
        return R.layout.activity_eventbus;
    }

    @Override
    protected void initView() {
        // 处理通知栏
        StatusBarUtil.setTranslucentStatus(this);
        StatusBarUtil.setAndroidNativeLightStatusBar(this, false);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        Button button = findViewById(R.id.bus_btn);
        edit_get = findViewById(R.id.edit_get);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(EventBusActivity1.this,EventBusActivity2.class);
            startActivity(intent);
        });
    }

    @Override
    protected void initData() {

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            Log.d(TAG, "onDestroy: EventBus.getDefault().unregister");
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageWrap messageWrap){
        Log.e(TAG, "onEvent: messageWrap" + messageWrap.message);
        edit_get.setText(messageWrap.message);
    }
}