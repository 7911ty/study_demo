package com.example.studydemo.ui.diyui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.text.DecimalFormat;
import java.util.Calendar;

public class FollowView extends View {
    private static final String TAG = "FollowView";
    private Paint textPaint;
    private String mText;

    public FollowView(Context context) {
        this(context, null);
    }

    public FollowView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FollowView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPoint();
    }

    private void initPoint() {
        textPaint = new Paint();
        textPaint.setColor(Color.RED);
        textPaint.setTextSize(60);
    }

    // 设置自定义文字
    public void setText(String text) {
        mText = text;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public FollowView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    // 结束时x位置
    private int mLastX;
    // 结束时y位置
    private int mLastY;

    float moveX;
    float moveY;


    /*@Override
    public boolean onTouchEvent(MotionEvent event) {
//        Log.d(TAG, "[onTouchEvent]: 开始时mLastX 值: " + mLastY);
        // getRawX( )即表示的是点击的位置距离屏幕的坐标 getX( )即表示的点击的位置相对于本身的坐标
        int x = (int) event.getRawX();
        Log.d(TAG, "[onTouchEvent]: 位置距离屏幕的x坐标: " + x);
        int y = (int) event.getRawY();
//        Log.d(TAG, "[onTouchEvent]: 位置距离屏幕的y坐标: " + y);
        //动画实现移动代码
        //--------------------------------------------------
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "[onTouchEvent]: ACTION_MOVE");
                Log.d(TAG, "[onTouchEvent]: 开始时mLastX 值: " + mLastX);
                // x代表滑动到的新位置，mLastX代表上一个位置，这两个的差就是真正要滑动的距离，将这个差值加上之前距离
                int delaltax = x - mLastX;
                int delaltaY = y - mLastY;
                float tranX = getTranslationX() + delaltax;
                float tranY = getTranslationY() + delaltaY;
                Log.d(TAG, "[onTouchEvent]: getTranslationX(): " + getTranslationX());
                Log.d(TAG, "[onTouchEvent]: tranX: " + tranX);
                setTranslationX(tranX);
                setTranslationY(tranY);
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        //-----------------------------------------------
        //getX getY实现移动代码
        //--------------------------------------------------
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                moveX = event.getX();
                moveY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                setTranslationX(getX() + (event.getX() - moveX));
                setTranslationY(getY() + (event.getY() - moveY));
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        //-----------------------------------------------

        //layout方法实现
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                break;
//            case MotionEvent.ACTION_MOVE:
//                //计算偏移量
//                int offsetX = x - mLastX;
//                int offsetY = y - mLastY;
//                //重新布局
//                layout(getLeft() + offsetX, getTop() + offsetY,
//                        getRight() + offsetX, getBottom() + offsetY);
//                //也可以使用下面这种方法
//        offsetLeftAndRight(offsetX);
//        offsetTopAndBottom(offsetY);
//                break;
//            case MotionEvent.ACTION_UP:
//                break;
//            default:
//                break;
//        }
        //---------------------------------------------

        //LayoutParams方法
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                break;
//            case MotionEvent.ACTION_MOVE:
//                int offsetX = x - mLastX;
//                int offsetY = y - mLastY;
//                //示例代码的父View是LinearLayout
//                ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
//                layoutParams.leftMargin = getLeft() + offsetX;
//                layoutParams.topMargin = getTop() + offsetY;
//                //下面这两句都可以使用，setLayoutParams也会调用requestLayout
//                //                setLayoutParams(layoutParams);
//                requestLayout();
//                break;
//            case MotionEvent.ACTION_UP:
//                break;
//            default:
//                break;
//        }
        //---------------------------------------------
        mLastX = x;
        mLastY = y;
        return true;
    }*/

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        //每隔1s重新绘制
        postInvalidateDelayed(1);

        Calendar calendar = Calendar.getInstance();
        int hour12 = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        int millisecond = calendar.get(Calendar.MILLISECOND);
        //小时采用24小时制返回
        int h = calendar.get(Calendar.HOUR_OF_DAY);
        String currentTime = intAdd0(hour12) + ":" + intAdd0(minute) + ":" + intAdd0(second)+ " " + millisecond;

        if (onCurrentTimeListener != null) {
           onCurrentTimeListener.currentTime(currentTime);
        }
        canvas.drawText(currentTime, 0, 50, textPaint);

    }
    //获取时间监听
    private OnCurrentTimeListener onCurrentTimeListener;

    public void setOnCurrentTimeListener(OnCurrentTimeListener onCurrentTimeListener) {
        this.onCurrentTimeListener = onCurrentTimeListener;
    }
    public interface OnCurrentTimeListener {
        void currentTime(String time);
    }

    /**
     * int小于10的添加0
     *
     * @param i
     * @return
     */
    private String intAdd0(int i) {
        DecimalFormat df = new DecimalFormat("00");
        if (i < 10) {
            return df.format(i);
        } else {
            return i + "";
        }
    }
}