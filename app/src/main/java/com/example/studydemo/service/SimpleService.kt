package com.example.studydemo.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class SimpleService : Service() {
    private  val TAG = "SimpleService"
    override fun onBind(intent: Intent): IBinder? {
       return null
    }

    /**
     * 首次创建服务时，系统将调用此方法来执行一次性设置程序（在调用 onStartCommand() 或 onBind() 之前）。
     * 如果服务已在运行，则不会调用此方法。该方法只被调用一次
     */
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: ")
    }

    /**
     * 每次通过startService()方法启动Service时都会被回调
     */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand: ")
        return super.onStartCommand(intent, flags, startId)
    }

    /**
     * 服务销毁时的回调
     */
    override fun onDestroy() {
        Log.d(TAG, "onDestroy: ")
        super.onDestroy()
    }
}