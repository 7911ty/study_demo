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
        for (i in 1..10) {

        }
        recyclerview.adapter = CoordinatorLayoutAdapter(
            this, arrayListOf(
                CoordinatorBean("title 1", "https://images.pexels.com/photos/14437081/pexels-photo-14437081.jpeg?auto=compress&cs=tinysrgb&w=600&lazy=load"),
                CoordinatorBean("title 1", "https://images.pexels.com/photos/14437083/pexels-photo-14437083.jpeg?auto=compress&cs=tinysrgb&w=600&lazy=load"),
                CoordinatorBean("title 1", "https://images.pexels.com/photos/14437084/pexels-photo-14437084.jpeg?auto=compress&cs=tinysrgb&w=600&lazy=load"),
                CoordinatorBean("title 1", "https://images.pexels.com/photos/14437085/pexels-photo-14437085.jpeg?auto=compress&cs=tinysrgb&w=600&lazy=load"),
                CoordinatorBean("title 1", "https://images.pexels.com/photos/14437086/pexels-photo-14437086.jpeg?auto=compress&cs=tinysrgb&w=600&lazy=load"),
                CoordinatorBean("title 1", "https://images.pexels.com/photos/14437087/pexels-photo-14437087.jpeg?auto=compress&cs=tinysrgb&w=600&lazy=load")
            )
        )
    }

    private fun initTopImageView(): ImageView? {
        val imageView = findViewById<ImageView>(R.id.imageview)
        Glide.with(this)
            .load("https://images.pexels.com/photos/14437082/pexels-photo-14437082.jpeg?auto=compress&cs=tinysrgb&w=600&lazy=load")
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
            } else {
                myTitleView.visibility = ViewGroup.VISIBLE
                myTitleView.alpha = percent
            }
        })
    }
}