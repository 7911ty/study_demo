package com.example.studydemo.ui.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * 用来验证是否还能接受的第二个有序广播
 */
public class AnotherBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"AnotherBroadcastReceiver",Toast.LENGTH_LONG).show();
    }
}
