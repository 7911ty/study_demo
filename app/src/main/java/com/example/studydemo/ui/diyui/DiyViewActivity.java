package com.example.studydemo.ui.diyui;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.base.utils.BaseUtils;
import com.example.studydemo.R;

public class DiyViewActivity extends AppCompatActivity {
    private static final String TAG = "DiyViewActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diy_view);
        initView();
    }

    private void initView() {
        FollowView time = findViewById(R.id.fv_time);
        time.setText(BaseUtils.getTime());
        time.setOnCurrentTimeListener(new FollowView.OnCurrentTimeListener() {
            @Override
            public void currentTime(String time) {
                Log.d(TAG, "currentTime: time = " + time);
            }
        });
    }
}