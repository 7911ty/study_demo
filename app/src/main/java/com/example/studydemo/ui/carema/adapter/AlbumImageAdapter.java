package com.example.studydemo.ui.carema.adapter;

import android.content.Context;

import com.example.studydemo.base.baseadapter.MultiItemTypeAdapter;
import com.example.studydemo.bean.album.ImageInfo;
import com.example.studydemo.ui.carema.adapter.delegate.DelegateAlbumDelegate;

import java.util.List;

public class AlbumImageAdapter extends MultiItemTypeAdapter<ImageInfo> {
    public AlbumImageAdapter(Context context, List<ImageInfo> datas) {
        super(context, datas);
        addItemViewDelegate(new DelegateAlbumDelegate(context) );
    }
}
