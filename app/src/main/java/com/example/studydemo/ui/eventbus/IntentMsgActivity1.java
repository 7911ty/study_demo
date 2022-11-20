package com.example.studydemo.ui.eventbus;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;

import com.example.studydemo.R;
import com.example.studydemo.base.BaseActivity;

/**
 * 使用intent进行activity之间进行传值
 */
public class IntentMsgActivity1 extends BaseActivity {

    @Override
    protected int initLayout() {
        return R.layout.activity_eventbus;
    }

    @Override
    protected void initView() {
        ActivityResultLauncher<Intent> intentActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    Intent data = result.getData();
                    int resultCode = result.getResultCode();
                    if ((resultCode == 0x12)) {
                        Bundle bundle = data.getExtras();
                        String name = bundle.getString("name");
                        String password = bundle.getString("password");
                        Toast.makeText(context, name + " " + password, Toast.LENGTH_SHORT).show();
                    }
                });
        Button button = findViewById(R.id.bus_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntentMsgActivity1.this, IntentMsgActivity2.class);
                //java写法
                intentActivityResultLauncher.launch(new Intent(context, IntentMsgActivity2.class));
//                startActivityForResult(intent,001);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == 001)) {
            Bundle bundle = data.getExtras();
            String name = bundle.getString("name");
            String password = bundle.getString("password");
            Toast.makeText(this, name + " " + password, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}
