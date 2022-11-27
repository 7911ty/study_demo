package com.example.studydemo.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.studydemo.R;

/**
 * 标题
 */
public class MyTitleView extends LinearLayout {

    private final Activity mContent;
    private TextView titleBar;

    public MyTitleView(Context context) {
        this(context,null);
    }

    public MyTitleView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContent = (Activity) context;
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.my_title_view, this, false);
        titleBar = view.findViewById(R.id.my_title_view);
        RelativeLayout titleRoot = view.findViewById(R.id.title_root);
//        titleRoot.setBackgroundColor(ColorUtil.getColorByRgb(null));
        ImageView blackIv = view.findViewById(R.id.black_iv);
        blackIv.setOnClickListener(view1 -> mContent.finish());
        addView(view);
    }
    public void setText(String str){
        if (titleBar != null) {
            titleBar.setText(str);
        }
    }
}
