package com.example.studydemo.adapter.delegate

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.base.utils.GlideUtils
import com.example.studydemo.R
import com.example.studydemo.base.baseadapter.base.ItemViewDelegate
import com.example.studydemo.base.baseadapter.base.ViewHolder
import com.example.studydemo.bean.coordinator.CoordinatorBean

import kotlinx.android.synthetic.main.delegate_coordinatorlayout.view.*

class CoordinatorLayoutDelegate(context: Context?) : ItemViewDelegate<CoordinatorBean> {
    lateinit var mContent: Context
    private val TAG = "CoordinatorLayoutDelega"

    init {
        if (context != null) {
            mContent = context
        }

    }

    override fun getItemViewLayoutId(): Int {
        return R.layout.delegate_coordinatorlayout
    }

    override fun isForViewType(item: CoordinatorBean?, position: Int): Boolean {
        if (item != null) {
            return true
        }
        return false
    }

    override fun convert(holder: ViewHolder?, item: CoordinatorBean?, position: Int) {
        val coordinatorlayoutIv = holder?.itemView?.coordinatorLayout_iv
        val coordinatorlayoutTv = holder?.itemView?.coordinatorLayout_tv
        coordinatorlayoutTv?.text = item?.title
        if (coordinatorlayoutIv != null) {
            if (item != null) {
                GlideUtils.loadImage(
                    mContent as Activity?,
                    item.imageUrl,
                    coordinatorlayoutIv,
                    object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            Log.d(TAG, "onLoadFailed: ")
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            Log.d(TAG, "onResourceReady: ")
                            val imageWidth = resource?.intrinsicWidth
                            val imageHeight = resource?.intrinsicHeight
                            val layoutParams = coordinatorlayoutIv.layoutParams
                            if (imageWidth != null) {
                                layoutParams.width = imageWidth
                            }
                            if (imageHeight != null) {
                                layoutParams.height = imageHeight
                            }
                            coordinatorlayoutIv.layoutParams = layoutParams
                            coordinatorlayoutIv.setImageDrawable(resource)
                            return true
                        }
                    })
            }
        }
    }


}