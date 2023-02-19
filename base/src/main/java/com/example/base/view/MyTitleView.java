package com.example.base.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.base.R;

/**
 * 标题
 */
public class MyTitleView extends LinearLayout {

    private final Activity mContent;
    private TextView titleBar;
    private ImageView right1;
    private ImageView right2;

    public ImageView getRight1() {
        return right1;
    }

    public ImageView getRight2() {
        return right2;
    }

    private TitleClickListener titleClickListener;

    public void setTitleClickListener(TitleClickListener titleClickListener) {
        this.titleClickListener = titleClickListener;
    }

    public MyTitleView(Context context) {
        this(context, null);
    }

    public MyTitleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContent = (Activity) context;
        initView();
    }

    private void initView() {
        View root = LayoutInflater.from(getContext()).inflate(R.layout.my_title_view, this, false);
        titleBar = root.findViewById(R.id.my_title_view);
        right1 = root.findViewById(R.id.iv_right1);
        right2 = root.findViewById(R.id.iv_right2);
        ImageView blackIv = root.findViewById(R.id.black_iv);
        blackIv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                titleClickListener.blackClick();
            }
        });
        right1.setOnClickListener(view -> titleClickListener.right1Click());
        right2.setOnClickListener(view -> titleClickListener.right2Click());
        addView(root);
    }

    public void setText(String str) {
        if (titleBar != null) {
            titleBar.setText(str);
        }
    }

    public void setRight1Drawable(Drawable drawable) {
        if (right1 == null) {
            return;
        }
        right1.setImageDrawable(drawable);
    }

    public void setRight1Visibility(int visibility) {
        if (right1 == null) {
            return;
        }
        right1.setVisibility(visibility);
    }


    public void setRight1ClickListener(View.OnClickListener clickListener) {
        if (right1 == null) {
            return;
        }

    }

    public void setRight2Drawable(Drawable drawable) {
        if (right2 == null) {
            return;
        }
        right2.setImageDrawable(drawable);
    }


}
