package com.example.studydemo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver2 extends BroadcastReceiver {
    private static final String TAG = "MyReceiver2";
    @Override
    public void onReceive(Context context, Intent intent) {
        String intentAction = intent.getAction();
        Log.d(TAG, "onReceive: intentAction = " + intentAction);
        Toast.makeText(context,"接受到广播 received in MyReceiver2 , intentAction = " + intentAction,Toast.LENGTH_LONG).show();
        // 拦截广播
        abortBroadcast();
    }
}