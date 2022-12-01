package com.example.studydemo.ui.coordinatorlayout

import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.studydemo.R
import com.example.studydemo.adapter.CoordinatorLayoutAdapter
import com.example.studydemo.base.BaseActivity
import com.example.studydemo.bean.coordinator.CoordinatorBean
import com.example.studydemo.utils.StatusBarUtil
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_coordinatorlayout.*
import java.lang.Math.abs

class CoordinatorLayoutActivity : BaseActivity() {
    override fun initLayout(): Int {
        return R.layout.activity_coordinatorlayout
    }

    override fun initView() {
        val imageView = initTopImageView()
        if (imageView != null) {
            initAppBar(imageView)
        }
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        var arrayListOf = arrayListOf<CoordinatorBean>()
        for (i in 1..10) {
            arrayListOf.add(
                CoordinatorBean(
                    "title $i",
                    "http://unsplash.it/1080/" + (2000 + i) + "?random"
                )
            )
        }
        recyclerview.adapter = CoordinatorLayoutAdapter(this, arrayListOf)
    }

    private fun initTopImageView(): ImageView? {
        val imageView = findViewById<ImageView>(R.id.imageview)
        Glide.with(this)
            .load("http://unsplash.it/1080/680?random")
            .into(imageView)

        return imageView
    }

    private fun initAppBar(imageView: ImageView) {
        val appBarLayout = findViewById<AppBarLayout>(R.id.app_bar_title)
        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            //计算imgBg从开始移动到最后的百分比
            val offset = abs(verticalOffset).toFloat()
            var percent = offset / (appBarLayout.totalScrollRange.toFloat() * 0.7f)
            //百分大于1，直接赋值为1
            if (percent >= 1) {
                percent = 1f
            }
            val alpha = 1 - percent
            Log.d(TAG, "initView: alpha = $alpha")
            //设置透明度
            imageView.alpha = alpha
            if (alpha > 0.8f) {
                myTitleView.visibility = ViewGroup.GONE
                StatusBarUtil.setAndroidNativeLightStatusBar(this, false);
            } else {
                myTitleView.visibility = ViewGroup.VISIBLE
                myTitleView.alpha = percent
                StatusBarUtil.setAndroidNativeLightStatusBar(this, true);
            }
        })
    }
}