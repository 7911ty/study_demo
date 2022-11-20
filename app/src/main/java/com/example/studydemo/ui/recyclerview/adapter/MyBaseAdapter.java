package com.example.studydemo.ui.recyclerview.adapter;

import android.content.Context;

import com.example.studydemo.base.baseadapter.MultiItemTypeAdapter;
import com.example.studydemo.ui.recyclerview.beans.BaseAdapterBean;
import com.example.studydemo.ui.recyclerview.delegate.BaseAdapterNo1Delegate;
import com.example.studydemo.ui.recyclerview.delegate.BaseAdapterNo2Delegate;

import java.util.List;

public class MyBaseAdapter extends MultiItemTypeAdapter<BaseAdapterBean> {

    public MyBaseAdapter(Context context, List<BaseAdapterBean> datas) {
        super(context, datas);
        addItemViewDelegate(new BaseAdapterNo1Delegate(context));
        addItemViewDelegate(new BaseAdapterNo2Delegate(context));
    }
}
