package com.example.studydemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class textView extends View {
    public textView(Context context) {
        super(context);
    }

    public textView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint textPaint = new Paint();          // 创建画笔
        textPaint.setColor(Color.RED);        // 设置颜色
        textPaint.setStyle(Paint.Style.FILL);   // 设置样式
        textPaint.setTextSize(50);              // 设置字体大小
        String str = "SLOOP";

        canvas.drawPosText(str,new float[]{
                100,100,    // 第一个字符位置
                100,200,    // 第二个字符位置
                100,300,    // ...
                100,400,
                100,500
        },textPaint);
    }
}
