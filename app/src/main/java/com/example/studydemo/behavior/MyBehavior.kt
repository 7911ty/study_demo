package com.example.studydemo.behavior

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.studydemo.ui.diyui.DownMoveView

class MyBehavior @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : CoordinatorLayout.Behavior<View>(context, attrs) {


    /**
     *  使用该Behavior的View要监听哪个类型的View的状态变化
     *  @param parent CoordinatorLayout
     *  @param child 使用该Behavior的View
     *  @param dependency dependency代表要监听的View
     */
    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        //这儿我们要监听DownMoveView
        return dependency is DownMoveView
    }

    /**
     * 当被监听的View状态变化时会调用该方法(被监听的View状态变化必须是使用layoutParams方法进行位置变换)
     * 参数同上一个方法一致
     * child -> View2
     * dependency -> view1
     * 使View2永远在View1下方100px位置
     */
    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        val view2Param = child.layoutParams as CoordinatorLayout.LayoutParams
        view2Param.topMargin = dependency.top + dependency.height + 100
        view2Param.leftMargin = dependency.left
        child.layoutParams = view2Param
        return true
    }
}