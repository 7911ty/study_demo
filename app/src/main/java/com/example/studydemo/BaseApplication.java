package com.example.studydemo;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.squareup.leakcanary.LeakCanary;

public class BaseApplication extends Application {
    private static BaseApplication mApplication;
    private static int mMainTid;//主线程id
    private static Handler mHandler;//主线程处理handler

    @Override
    public void onCreate() {
        mApplication = this;
        mMainTid = android.os.Process.myTid();
        mHandler = new Handler();
        super.onCreate();
//        initLeakCanary();
    }
    private void initLeakCanary(){
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }
    public static Context getContext(){
        return mApplication;
    }
    public static BaseApplication getApplication() {
        return mApplication;
    }

    public static int getMainTid() {
        return mMainTid;
    }

    public static Handler getHandler() {
        return mHandler;
    }

}