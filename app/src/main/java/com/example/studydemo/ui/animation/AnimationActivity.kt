package com.example.studydemo.ui.animation

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import android.util.Log
import android.view.View
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

    private val imageView1: ImageView?
        get() {
            val iv_frame_animation = findViewById<ImageView>(R.id.iv_frame_animation)
            return iv_frame_animation
        }

    override fun initView() {
        val iv_photo = imageView
        val button = findViewById<Button>(R.id.bt_stop_animation)
        val iv_frame_animation = findViewById<ImageView>(R.id.iv_frame_animation)
        // 通过xml实现view 动画
        var xmlAnimation = AnimationUtils.loadAnimation(this, R.anim.animation_all)
        //设置动画播放时长
        xmlAnimation.duration = 5000
        // iv_photo?.startAnimation(xmlAnimation)
        // 通过代码实现view 动画
        blendAnimation()


        initEvent(iv_photo)
        button.setOnClickListener {
            iv_photo?.clearAnimation();
        }

        // 设置帧动画
        val animationDrawable =
            ContextCompat.getDrawable(this, R.drawable.frame_animation) as AnimationDrawable
        iv_frame_animation.background = animationDrawable
        animationDrawable.start()
        playAnimation()
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
//        iv_photo?.startAnimation(mAnimationSet)
    }

    private fun initEvent(iv_photo: ImageView?) {
        iv_photo?.setOnClickListener {
            showToast("图片点击")
        }
    }
    /*ObjectAnimator的使用*/
    fun playObjectAnimation(){
        val set = AnimatorSet()
        //控制透明度的，我用ofInt没好使，还是用float吧
        var animator1= ObjectAnimator.ofFloat(iv_photo,"alpha",0f,1f)
        animator1?.duration=2000
        //延X轴移动
        var animator2=ObjectAnimator.ofFloat(iv_photo,"translationX",0f,400f)
        animator2?.duration=2000
        animator2?.interpolator=DecelerateInterpolator()
        //延y轴移动,起始位置和结束位置
        var animator3=ObjectAnimator.ofFloat(iv_photo,"translationY",0f,500f,200f,400f,100f)
        animator3?.duration=2000
        animator3?.interpolator=DecelerateInterpolator()
        //沿X轴旋转
        var animator4=ObjectAnimator.ofFloat(iv_photo,"rotationX",0f,720f)
        animator4?.duration=2000
        //沿Y轴旋转
        var animator5=ObjectAnimator.ofFloat(iv_photo,"rotationY",0f,720f)
        animator5?.duration=2000
        //缩放,可以根据X轴和Y轴缩放
        var animator6=ObjectAnimator.ofFloat(iv_photo,"scaleX",0f,4f,2f)
        animator6?.duration=3000
        //改变颜色
        var animator7=ObjectAnimator.ofInt(iv_photo,"backgroundColor",
            Color.RED, Color.BLUE, Color.GRAY, Color.GREEN)
        animator7?.duration=2000
        set.play(animator1).before(animator2) //先执行anim动画之后在执行anim2
        set.play(animator2).before(animator3)
        set.play(animator3).before(animator4)
        set.play(animator4).before(animator5)
        set.play(animator5).before(animator6)
        set.play(animator6).before(animator7)
        set.start()
    }
    fun playAnimation(){
        //获得xml中TextView的宽度和高度
        val spec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        iv_photo.measure(spec, spec)
        val measuredWidth = iv_photo.measuredWidth
        val measuredHeight = iv_photo.measuredHeight
        Log.d(TAG, "playAnimation: measuredWidth = $measuredWidth measuredHeight = $measuredHeight")
        // ofInt(),其中还包括ofFloat，区别只是写入的内容不同，作用有：
        // 1. 创建动画实例
        // 2. 将传入的多个Int参数进行平滑过渡:此处传入0和400,表示将值从0平滑过渡到400
        // 如果传入了3个Int参数 a,b,c ,则是先从a平滑过渡到b,再从b平滑过渡到C，以此类推
        // ValueAnimator.ofInt()内置了整型估值器,直接采用默认的.不需要设置，即默认设置了如何从初始值 过渡到 结束值
        val anim = ValueAnimator.ofInt(0,400)
        // 设置动画的播放各种属性
        // 设置动画运行的时长
        anim?.duration=5000
        // 设置动画延迟播放时间
        anim?.startDelay=1000
        // 设置动画重复播放次数 = 重放次数+1
        // 动画播放次数 = infinite时,动画无限重复
        anim?.repeatCount=1
        // 设置重复播放动画模式
        // ValueAnimator.RESTART(默认):正序重放
        // ValueAnimator.REVERSE:倒序回放
        anim?.repeatMode=ValueAnimator.RESTART
        //动画改变监听器，5ms/帧
        anim?.addUpdateListener { animation ->
            // 获得改变后的值
            var curValue : Int=animation.animatedValue as Int
            Log.i("feng","时间值____"+curValue)
            // 将改变的值手动赋值给对象的属性值：通过动画的更新监听器
            // 设置 值的更新监听器
            // 即：值每次改变、变化一次,该方法就会被调用一次
            // 输出改变后的值:依次代表控件的左端、顶端、右端、和底端
            iv_photo?.layout(curValue,curValue,curValue+measuredWidth,curValue+measuredHeight)
        }
        anim?.start()
        // 启动动画
    }
}