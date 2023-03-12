package com.example.studydemo.ui.receiver;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.base.utils.ColorUtil;
import com.example.base.utils.DisplayUtil;
import com.example.biji.ui.MyBiJiActivity;
import com.example.studydemo.R;
import com.example.studydemo.base.baseadapter.MultiItemTypeAdapter;
import com.example.studydemo.base.baseadapter.base.ItemViewDelegate;
import com.example.studydemo.base.baseadapter.base.ViewHolder;
import com.example.studydemo.bean.MainBean;
import com.example.studydemo.service.SimpleServiceActivity;
import com.example.studydemo.ui.SecondActivity;
import com.example.studydemo.ui.animation.AnimationActivity;
import com.example.studydemo.ui.camera.OpenCameraActivity;
import com.example.studydemo.ui.carema.Camera3Activity;
import com.example.studydemo.ui.constraintlayout.ConstraintLayoutActivity;
import com.example.studydemo.ui.coordinatorlayout.MyCoordinatorLayoutActivity;
import com.example.studydemo.ui.diyui.DiyViewActivity;
import com.example.studydemo.ui.diyui.leafloading.LeafLoadingActivity;
import com.example.studydemo.ui.diyui.suspension.XuanFuActivity;
import com.example.studydemo.ui.eventbus.EventBusActivity1;
import com.example.studydemo.ui.eventbus.IntentMsgActivity1;
import com.example.studydemo.ui.fragment.MyFragmentActivity;
import com.example.studydemo.ui.handler.HandlerActivity;
import com.example.studydemo.ui.launchmode.LaunchModeActivity;
import com.example.studydemo.ui.listview.MyListViewActivity;
import com.example.studydemo.ui.okhttp.NetworkActivity;
import com.example.studydemo.ui.recyclerview.MyBaseAdapterActivity;
import com.example.studydemo.ui.storage.StorageActivity1;
import com.example.studydemo.viewpage.ViewpageAndFragmentActivity;

import java.util.List;

public class MainAdapter extends MultiItemTypeAdapter<MainBean> {
    private static final String TAG = "MainAdapter";
    private Context mContext;

    public MainAdapter(Context context, List datas) {
        super(context, datas);
        mContext = context;
        addItemViewDelegate(new MainDelegate());
    }

    class MainDelegate implements ItemViewDelegate<MainBean> {
        @Override
        public int getItemViewLayoutId() {
            return R.layout.item_main;
        }

        @Override
        public boolean isForViewType(MainBean item, int position) {
            return true;
        }

        @Override
        public void convert(ViewHolder holder, MainBean mainBean, int position) {
            int screenWidth = DisplayUtil.getScreenWidth(mContext);
            Button button = holder.itemView.findViewById(R.id.main_bt);
            ViewGroup.LayoutParams layoutParams = button.getLayoutParams();
            layoutParams.width = screenWidth / 2;
            layoutParams.height = screenWidth / 2;
            button.setLayoutParams(layoutParams);
            button.setText(mainBean.getName());
            button.setBackgroundColor(ColorUtil.getColorByRgb(""));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CharSequence text = button.getText();
                    Log.d(TAG, "onItemClick: text = " + text);
                    Log.d(TAG, "onItemClick: position = " + position);
                    Log.d(TAG, "onItemClick: view.tostring = " + view.toString());
                    Intent intent = null;
                    switch (text.toString()) {
                        case "listview_bt":
                            intent = new Intent(mContext, MyListViewActivity.class);
                            break;
                        case "笔记":
                            intent = new Intent(mContext, MyBiJiActivity.class);
                            break;
                        case "bt_3_camera":
                            intent = new Intent(mContext, Camera3Activity.class);
                            break;
                        case "bt_4_fragment":
                            intent = new Intent(mContext, MyFragmentActivity.class);
                            break;
                        case "bt_5_viewpage":
                            intent = new Intent(mContext, ViewpageAndFragmentActivity.class);
                            break;
                        case "bt_6_eventbus":
                            intent = new Intent(mContext, EventBusActivity1.class);
                            break;
                        case "bt_6_2_intent":
                            intent = new Intent(mContext, IntentMsgActivity1.class);
                            break;
                        case "bt_7_broadcast_receiver":
                            intent = new Intent(mContext, MyBroadcastReceiverActivity.class);
                            break;
                        case "bt_8_baseadapter":
                            intent = new Intent(mContext, MyBaseAdapterActivity.class);
                            break;
                        case "bt_9_dialog":
                            intent = new Intent(mContext, SecondActivity.class);
                            break;
                        case "bt_10_camera":
                            intent = new Intent(mContext, OpenCameraActivity.class);
                            break;
                        case "bt_11_diy":
                            intent = new Intent(mContext, DiyViewActivity.class);
                            break;
                        case "bt_12_leaf":
                            intent = new Intent(mContext, LeafLoadingActivity.class);
                            break;
                        case "bt_13_okhttp":
                            intent = new Intent(mContext, NetworkActivity.class);
                            break;
                        case "bt_14_xuanfuchuang":
                            intent = new Intent(mContext, XuanFuActivity.class);
                            break;
                        case "bt_15_handler":
                            intent = new Intent(mContext, HandlerActivity.class);
                            break;
                        case "bt_16_storage":
                            intent = new Intent(mContext, StorageActivity1.class);
                            break;
                        case "bt_17_mycoordinator":
                            intent = new Intent(mContext, MyCoordinatorLayoutActivity.class);
                            break;
                        case "bt_18_activity的启动模式":
                            intent = new Intent(mContext, LaunchModeActivity.class);
                            break;
                        case "bt_19_start_service":
                            intent = new Intent(mContext, SimpleServiceActivity.class);
                            break;
                        case "bt_20_view_animation":
                            intent = new Intent(mContext, AnimationActivity.class);
                            break;
                        case "bt_21_约束布局":
                            intent = new Intent(mContext, ConstraintLayoutActivity.class);
                            break;
                    }
                    mContext.startActivity(intent);
                }
            });
        }
    }

    @Override
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        super.setOnItemClickListener(onItemClickListener);
    }

}
