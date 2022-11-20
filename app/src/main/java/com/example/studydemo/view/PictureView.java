package com.example.studydemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Picture;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.studydemo.R;

public class PictureView extends View {
    // 画笔
    private Paint paint = new Paint();
    // 宽高
    private int mWidth, mHeight;

    public PictureView(Context context) {
        this(context, null);
    }

    public PictureView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PictureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        recording();    // 调用录制
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    // 1.创建Picture
    private Picture mPicture = new Picture();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        mPicture.draw(canvas);
        Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.pikaqi);
        canvas.drawBitmap(bitmap,new Matrix(),new Paint());
    }

    // 2.录制内容方法
    private void recording() {
        // 开始录制 (接收返回值Canvas)
        Canvas canvas = mPicture.beginRecording(500, 500);
        // 创建一个画笔
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);

        // 在Canvas中具体操作
        // 位移
        canvas.translate(250, 250);
        // 绘制一个圆
        canvas.drawCircle(0, 0, 100, paint);
//        Bitmap bitmap = BaseUtils.netToLoacalBitmap("https://home.cnblogs.com/u/yiXiuRuShan/");
//        canvas.drawBitmap(bitmap,new Matrix(),new Paint());
        mPicture.endRecording();
    }
}
