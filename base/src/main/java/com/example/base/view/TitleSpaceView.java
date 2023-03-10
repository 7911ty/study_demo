package com.example.base.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.base.utils.DisplayUtil;

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
        int statusBarHeight1 = 0;
//获取status_bar_height资源的ID
        int resourceId =getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight1 = getResources().getDimensionPixelSize(resourceId);
        }
        int height = DisplayUtil.dip2px(mContent, 48) + statusBarHeight1;
        Log.d(TAG, "onMeasure: height  = " + height );
        setMeasuredDimension(ViewGroup.LayoutParams.MATCH_PARENT, height);
    }
}
