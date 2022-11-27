package com.example.studydemo.utils;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;

public class GlideUtils {
    public static void loadImage(Activity context,
                     String url,
                     ImageView imageView,
                     RequestListener<Drawable> listener) {
        if (activityNotDestroyed(context)) {
            Glide.with(context)
                    .load(url)
                    .centerCrop()
                    .listener(listener)
                    .into(imageView);
        }
    }

    public static boolean activityNotDestroyed(Activity activity) {
        return activity != null && !activity.isDestroyed() && !activity.isFinishing();
    }
}
