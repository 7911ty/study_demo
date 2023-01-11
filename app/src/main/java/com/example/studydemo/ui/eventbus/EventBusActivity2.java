package com.example.studydemo.ui.eventbus;

import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.base.ui.BaseActivity;
import com.example.base.utils.StatusBarUtil;
import com.example.studydemo.R;
import com.example.studydemo.bean.MessageWrap;

import org.greenrobot.eventbus.EventBus;

public class EventBusActivity2 extends BaseActivity {

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
        button.setOnClickListener(v -> {
            String msg = editText.getText().toString();
            EventBus.getDefault().post(MessageWrap.getInstance(msg, 1));
            Log.e(TAG, "onClick: EventBus发送数据" + msg);
            Toast.makeText(EventBusActivity2.this, msg, Toast.LENGTH_LONG).show();
        });
    }
}
