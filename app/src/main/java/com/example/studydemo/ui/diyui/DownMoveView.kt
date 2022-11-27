package com.example.studydemo.ui.diyui

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout

class DownMoveView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    private var lastX = 0f
    private var lastY = 0f

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        // 相对于屏幕的位置
        val eventX = event.rawX
        val eventY = event.rawY
        when (event.action) {
            MotionEvent.ACTION_MOVE -> {
                val layoutParams = this.layoutParams as CoordinatorLayout.LayoutParams
                layoutParams.leftMargin = (layoutParams.leftMargin + eventX - lastX).toInt()
                layoutParams.topMargin = (layoutParams.topMargin + eventY - lastY).toInt()
                setLayoutParams(layoutParams)
            }
            else -> {}
        }
        lastX = eventX
        lastY = eventY
        return true
    }
}