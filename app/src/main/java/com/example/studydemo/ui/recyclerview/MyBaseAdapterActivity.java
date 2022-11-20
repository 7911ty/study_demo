package com.example.studydemo.ui.recyclerview;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studydemo.R;
import com.example.studydemo.ui.recyclerview.adapter.MyBaseAdapter;
import com.example.studydemo.ui.recyclerview.beans.BaseAdapterBean;
import com.example.studydemo.ui.recyclerview.beans.DelegateNo1Bean;
import com.example.studydemo.ui.recyclerview.beans.DelegateNo2Bean;

import java.util.ArrayList;

public class MyBaseAdapterActivity extends AppCompatActivity {

    private ArrayList<BaseAdapterBean> baseAdapterBeans;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mybase_adapter_activity);
        RecyclerView recyclerView = findViewById(R.id.baseadapter_rv);
        baseAdapterBeans = new ArrayList<>();
        initData();
        recyclerView.setLayoutManager(new LinearLayoutManager(MyBaseAdapterActivity.this));
        MyBaseAdapter myBaseAdapter = new MyBaseAdapter(this, baseAdapterBeans);
        recyclerView.setAdapter(myBaseAdapter);
    }

    private void initData() {
        BaseAdapterBean baseAdapterBean = new BaseAdapterBean();
        DelegateNo1Bean delegateNo1Bean = new DelegateNo1Bean();
        delegateNo1Bean.setTextTitle("孤勇者");
        delegateNo1Bean.setImageUrl("https://y.qq.com/music/photo_new/T002R300x300M000001uaPM93kxk1R_3.jpg?max_age=2592000");
        delegateNo1Bean.setTextAutor("陈奕迅");
        baseAdapterBean.setDelegateNo1Bean(delegateNo1Bean);
        baseAdapterBean.setDelegateNo1Bean(delegateNo1Bean);
        baseAdapterBean.setType("1");
        DelegateNo2Bean delegateNo2Bean = new DelegateNo2Bean();
        delegateNo2Bean.setType("2");
        delegateNo2Bean.setImageUrl("https://qpic.y.qq.com/music_cover/gHhYPhuIOzEjhb7GYVv733JFsC65PiaxIj3lpvbUGiaLLlfPLOEp4Jng/600?n=1");
        BaseAdapterBean baseAdapterBean2 = new BaseAdapterBean();
        baseAdapterBean2.setDelegateNo2Bean(delegateNo2Bean);
        baseAdapterBean2.setDelegateNo2Bean(delegateNo2Bean);
        baseAdapterBean2.setType("2");
        baseAdapterBeans.add(baseAdapterBean);
        baseAdapterBeans.add(baseAdapterBean2);
        baseAdapterBeans.add(baseAdapterBean);
        baseAdapterBeans.add(baseAdapterBean2);
        baseAdapterBeans.add(baseAdapterBean);
        baseAdapterBeans.add(baseAdapterBean2);
    }
}
