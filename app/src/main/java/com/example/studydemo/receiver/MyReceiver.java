package com.example.studydemo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * android.intent.action.BOOT_COMPLETED 接受开机广播
 */
public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "MyReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG, "onReceive: boot complete" );
        Toast.makeText(context,"开机关播 boot complete",Toast.LENGTH_LONG).show();
    }
}