package com.example.studydemo.viewpage;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.studydemo.R;
import com.example.studydemo.base.BaseActivity;
import com.example.studydemo.ui.fragment.BlankFragment;
import com.example.studydemo.utils.StatusBarUtil;

import java.util.ArrayList;

public class ViewpageAndFragmentActivity extends BaseActivity implements View.OnClickListener {

    private ViewPager2 viewPager2;
    private LinearLayout llWeiXin, llFriend, llFind, llMine;
    private ImageView lvWeiXin, lvFriend, lvFind, lvMine,newImage;

    @Override
    protected int initLayout() {
        return R.layout.activity_viewpage_fragment;
    }

    @Override
    protected void initView() {
        StatusBarUtil.setTranslucentStatus(this);
        StatusBarUtil.setAndroidNativeLightStatusBar(this, true);
        viewPager2 = findViewById(R.id.viewpage_id);
        ArrayList<Fragment> fragments = new ArrayList<>();
        BlankFragment weixinFragment = BlankFragment.newInstance("weixin");
        Bundle bundle = new Bundle();
        bundle.putString("param1","微信");
        bundle.putString("student","di yi ge student");
        weixinFragment.setArguments(bundle);
        fragments.add(weixinFragment);

        BlankFragment tell = BlankFragment.newInstance("通讯录");
        Bundle bundle2 = new Bundle();
        bundle2.putString("param1","通信录");
        bundle2.putString("student","di er ge student");
        tell.setArguments(bundle2);
        fragments.add(tell);
        BlankFragment friend = BlankFragment.newInstance("朋友圈");
        Bundle bundle3 = new Bundle();
        bundle3.putString("param1","朋友圈");
        bundle3.putString("student","di san ge student");
        friend.setArguments(bundle3);
        fragments.add(friend);
        BlankFragment mine = BlankFragment.newInstance("我的");
        Bundle bundle4 = new Bundle();
        bundle4.putString("param1","我的");
        bundle4.putString("student","di si ge student");
        mine.setArguments(bundle4);
        fragments.add(mine);
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), getLifecycle(), fragments);
        viewPager2.setAdapter(adapter);
        initViewpage();
        initTabView();
    }

    private void initTabView() {
        llWeiXin = findViewById(R.id.ll_weixin);
        llFriend = findViewById(R.id.ll_friend);
        llFind = findViewById(R.id.ll_find);
        llMine = findViewById(R.id.ll_mine);
        llWeiXin.setOnClickListener(this::onClick);
        llFriend.setOnClickListener(this::onClick);
        llFind.setOnClickListener(this::onClick);
        llMine.setOnClickListener(this::onClick);

        lvWeiXin = findViewById(R.id.image_weixin);
        lvFriend = findViewById(R.id.image_friend);
        lvFind = findViewById(R.id.image_find);
        lvMine = findViewById(R.id.image_mine);
        newImage= lvWeiXin;
    }

    private void initViewpage() {
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                changeImage(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);

            }
        });
    }

    private void changeImage(int position) {
        newImage.setSelected(false);
        switch (position){
            case R.id.ll_weixin:
                viewPager2.setCurrentItem(0);
            case 0:
                lvWeiXin.setSelected(true);
                newImage = lvWeiXin;
                break;
            case R.id.ll_friend:
                viewPager2.setCurrentItem(1);
            case 1:
                lvFriend.setSelected(true);
                newImage = lvFriend;
                break;
            case R.id.ll_find:
                viewPager2.setCurrentItem(2);
            case 2:
                lvFind.setSelected(true);
                newImage = lvFind;
                break;
            case R.id.ll_mine:
                viewPager2.setCurrentItem(3);
            case 3:
                lvMine.setSelected(true);
                newImage = lvMine;
                break;
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        changeImage(v.getId());
    }
}
