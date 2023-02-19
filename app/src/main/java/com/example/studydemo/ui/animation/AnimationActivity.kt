package com.example.studydemo.ui.animation

import android.graphics.drawable.AnimationDrawable
import android.view.animation.*
import android.widget.Button
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.example.base.ui.BaseActivity
import com.example.studydemo.R
import kotlinx.android.synthetic.main.activity_animation.*

class AnimationActivity : BaseActivity() {

    override fun initLayout(): Int {
        return R.layout.activity_animation
    }

    private val imageView: ImageView?
        get() {
            val iv_photo = findViewById<ImageView>(R.id.iv_photo)
            return iv_photo
        }

    override fun initView() {
        val iv_photo = imageView
        val button = findViewById<Button>(R.id.bt_stop_animation)
        val iv_frame_animation = findViewById<ImageView>(R.id.iv_frame_animation)
        // 通过xml实现view 动画
        var xmlAnimation = AnimationUtils.loadAnimation(this, R.anim.animation_all)
        //设置动画播放时长
        xmlAnimation.duration = 5000
//        iv_photo?.startAnimation(xmlAnimation)
        // 通过代码实现view 动画
        blendAnimation()


        initEvent(iv_photo)
        button.setOnClickListener {
            iv_photo?.clearAnimation();
        }

        // 设置帧动画
        val animationDrawable =
            ContextCompat.getDrawable(this,R.drawable.frame_animation) as AnimationDrawable
        iv_frame_animation.background = animationDrawable
        animationDrawable.start()
    }

    private fun blendAnimation() {
        /**
         * 创建一个AnimationSet，它能够同时执行多个动画效果
         * 构造方法的入参如果是“true”，则代表使用默认的interpolator，如果是“false”则代表使用自定义interpolator
         */
        val mAnimationSet = AnimationSet(true)
        //透明度动画，从完全透明到不透明，我们的动画都是float型的，所以，在写数字的时候，要加f
        val alphaAnima = AlphaAnimation(0.5f, 1.0f)

        /**
         *  创建一个旋转动画对象
         *  入参列表含义如下：
         *  1.fromDegrees：从哪个角度开始旋转
         *  2.toDegrees：旋转到哪个角度结束
         *  3.pivotXType：旋转所围绕的圆心的x轴坐标的类型，有ABSOLUT绝对坐标、RELATIVE_TO_SELF相对于自身坐标、RELATIVE_TO_PARENT相对于父控件的坐标
         *  4.pivotXValue：旋转所围绕的圆心的x轴坐标,0.5f表明是以自身这个控件的一半长度为x轴
         *  5.pivotYType：y轴坐标的类型
         *  6.pivotYValue：y轴坐标
         */
        val rotateAnim = RotateAnimation(
            0f, 1080f, Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        )

        /**
         *  创建一个缩放效果的动画
         *  入参列表含义如下：
         *  fromX：x轴的初始值
         *  toX：x轴缩放后的值
         *  fromY：y轴的初始值
         *  toY：y轴缩放后的值
         *  pivotXType：x轴坐标的类型，有ABSOLUT绝对坐标、RELATIVE_TO_SELF相对于自身坐标、RELATIVE_TO_PARENT相对于父控件的坐标
         *  pivotXValue：x轴的值，0.5f表明是以自身这个控件的一半长度为x轴
         *  pivotYType：y轴坐标的类型
         *  pivotYValue：轴的值，0.5f表明是以自身这个控件的一半长度为y轴
         */
        var scaleAnimation = ScaleAnimation(
            0f,
            1f,
            0f,
            1f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        );
        /**
         *  创建一个移动动画效果
         *  入参的含义如下：
         *  fromXType：移动前的x轴坐标的类型
         *  fromXValue：移动前的x轴的坐标
         *  toXType：移动后的x轴的坐标的类型
         *  toXValue：移动后的x轴的坐标
         *  fromYType：移动前的y轴的坐标的类型
         *  fromYValue：移动前的y轴的坐标
         *  toYType：移动后的y轴的坐标的类型
         *  toYValue：移动后的y轴的坐标
         */
        var translateAnimation = TranslateAnimation(
            Animation.RELATIVE_TO_SELF, 0f, Animation.ABSOLUTE, 360f,
            Animation.RELATIVE_TO_SELF, 0f, Animation.ABSOLUTE, 360f
        )
        mAnimationSet.addAnimation(alphaAnima)
        mAnimationSet.addAnimation(rotateAnim)
        mAnimationSet.addAnimation(scaleAnimation)
        mAnimationSet.addAnimation(translateAnimation)
        mAnimationSet.duration = 5000//动画持续时间时间
        mAnimationSet.interpolator = DecelerateInterpolator() //添加插值器，下面会有说明
        mAnimationSet.fillAfter = false
        iv_photo?.startAnimation(mAnimationSet)


    }

    private fun initEvent(iv_photo: ImageView?) {
        iv_photo?.setOnClickListener {
            showToast("图片点击")
        }
    }
}