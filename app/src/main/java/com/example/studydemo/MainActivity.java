package com.example.studydemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.base.ui.BaseActivity;
import com.example.base.utils.StatusBarUtil;
import com.example.studydemo.bean.MainBean;
import com.example.studydemo.ui.receiver.MainAdapter;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    private TimeChangeReceiver timeChangeReceiver;
    private RecyclerView main_rv;

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected String initTitleText() {
        return "MainActivity";
    }

    // 初始化view
    protected void initView() {
        // 处理通知栏
        StatusBarUtil.setAndroidNativeLightStatusBar(this, false);
        StatusBarUtil.setStatusBarColor(this, R.color.purple_200);
        main_rv = findViewById(R.id.main_rv);
//        StatusBarUtil.setActivityViewInBarBottom(this, main_rv);
        // 时间改变的广播
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.TIME_TICK");
        timeChangeReceiver = new TimeChangeReceiver();
        // 注册广播
//        registerReceiver(timeChangeReceiver, intentFilter);
    }

    class TimeChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(MainActivity.this, "tiemChange", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void initData() {
        ArrayList<MainBean> arrayList = new ArrayList();
        MainBean mainBean = new MainBean();
        mainBean.setName("listview_bt");
        arrayList.add(mainBean);
        mainBean = new MainBean();
        mainBean.setName("笔记");
        arrayList.add(mainBean);
        mainBean = new MainBean();
        mainBean.setName("bt_3_camera");
        arrayList.add(mainBean);
        mainBean = new MainBean();
        mainBean.setName("bt_4_fragment");
        arrayList.add(mainBean);
        mainBean = new MainBean();
        mainBean.setName("bt_5_viewpage");
        arrayList.add(mainBean);
        mainBean = new MainBean();
        mainBean.setName("bt_6_eventbus");
        arrayList.add(mainBean);
        mainBean = new MainBean();
        mainBean.setName("bt_6_2_intent");
        arrayList.add(mainBean);
        mainBean = new MainBean();
        mainBean.setName("bt_7_broadcast_receiver");
        arrayList.add(mainBean);
        mainBean = new MainBean();
        mainBean.setName("bt_8_baseadapter");
        arrayList.add(mainBean);
        mainBean = new MainBean();
        mainBean.setName("bt_9_dialog");
        arrayList.add(mainBean);
        mainBean = new MainBean();
        mainBean.setName("bt_10_camera");
        arrayList.add(mainBean);
        mainBean = new MainBean();
        mainBean.setName("bt_11_diy");
        arrayList.add(mainBean);
        mainBean = new MainBean();
        mainBean.setName("bt_12_leaf");
        arrayList.add(mainBean);
        mainBean = new MainBean();
        mainBean.setName("bt_13_okhttp");
        arrayList.add(mainBean);
        mainBean = new MainBean();
        mainBean.setName("bt_14_xuanfuchuang");
        arrayList.add(mainBean);
        mainBean = new MainBean();
        mainBean.setName("bt_15_handler");
        arrayList.add(mainBean);
        mainBean = new MainBean();
        mainBean.setName("bt_16_storage");
        arrayList.add(mainBean);
        mainBean = new MainBean();
        mainBean.setName("bt_17_mycoordinator");
        arrayList.add(mainBean);
        mainBean = new MainBean();
        mainBean.setName("bt_18_activity的启动模式");
        arrayList.add(mainBean);
        mainBean = new MainBean();
        mainBean.setName("bt_19_start_service");
        arrayList.add(mainBean);
        mainBean = new MainBean();
        mainBean.setName("bt_20_view_animation");
        arrayList.add(mainBean); mainBean = new MainBean();
        mainBean.setName("bt_21_约束布局");
        arrayList.add(mainBean);
        MainAdapter mainAdapter = new MainAdapter(this, arrayList);
        main_rv.setLayoutManager(new GridLayoutManager(this,2));
        main_rv.setAdapter(mainAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}