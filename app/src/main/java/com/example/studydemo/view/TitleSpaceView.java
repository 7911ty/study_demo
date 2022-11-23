package com.example.studydemo.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.studydemo.utils.DisplayUtil;
import com.example.studydemo.utils.StatusBarUtil;

public class TitleSpaceView extends LinearLayout {
    private static final String TAG = "TitleSpaceView";

    private final Context mContent;

    public TitleSpaceView(Context context) {
        this(context, null);
    }

    public TitleSpaceView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleSpaceView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContent = context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = StatusBarUtil.getStatusBar((Activity) mContent) + DisplayUtil.dip2px(mContent, 42);
        Log.d(TAG, "onMeasure: height  = " + height );
        setMeasuredDimension(ViewGroup.LayoutParams.MATCH_PARENT, height);
    }
}
