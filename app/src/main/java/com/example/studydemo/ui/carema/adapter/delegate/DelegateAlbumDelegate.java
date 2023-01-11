package com.example.studydemo.ui.carema.adapter.delegate;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.example.base.utils.GlideUtils;
import com.example.studydemo.R;
import com.example.studydemo.base.baseadapter.base.ItemViewDelegate;
import com.example.studydemo.base.baseadapter.base.ViewHolder;
import com.example.studydemo.bean.ImageInfo;

public class DelegateAlbumDelegate implements ItemViewDelegate<ImageInfo> {
    private Context mContext;

    public DelegateAlbumDelegate(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.delegate_album;
    }

    @Override
    public boolean isForViewType(ImageInfo item, int position) {
        if (item == null || TextUtils.isEmpty(item.getImagePath())) {
            return false;
        }
        return true;
    }

    @Override
    public void convert(ViewHolder holder, ImageInfo imageInfo, int position) {
        ImageView imageView = holder.itemView.findViewById(R.id.iv_album_image);
        GlideUtils.loadImage((Activity) mContext, imageInfo.getImagePath(), imageView, null);
    }
}
