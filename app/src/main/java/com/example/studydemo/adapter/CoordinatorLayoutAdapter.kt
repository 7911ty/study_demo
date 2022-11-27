package com.example.studydemo.adapter

import android.content.Context
import com.example.studydemo.adapter.delegate.CoordinatorLayoutDelegate
import com.example.studydemo.base.baseadapter.MultiItemTypeAdapter
import com.example.studydemo.bean.coordinator.CoordinatorBean

class CoordinatorLayoutAdapter(context: Context?, datas: ArrayList<CoordinatorBean>?) :
    MultiItemTypeAdapter<CoordinatorBean>(context, datas) {
    init {
        addItemViewDelegate(CoordinatorLayoutDelegate(context))
    }
}