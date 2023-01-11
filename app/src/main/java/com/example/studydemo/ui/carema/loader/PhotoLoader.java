package com.example.studydemo.ui.carema.loader;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import com.example.studydemo.bean.ImageInfo;

import java.util.ArrayList;
import java.util.List;

public class PhotoLoader implements LoaderManager.LoaderCallbacks<Cursor> {

    private final String[] PARAMS_IMAGE = {
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.DATE_ADDED,
            MediaStore.Images.Media._ID
    };
    public FragmentActivity activity;

    public PhotoLoader(FragmentActivity activity) {
        this.activity = activity;
        activity.getSupportLoaderManager().initLoader(0, null, this);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        return new CursorLoader(activity,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                PARAMS_IMAGE,
                null,
                null,
                PARAMS_IMAGE[2] + " DESC");
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        if (data == null) {
            return;
        }
        List<ImageInfo> wholeImages = new ArrayList<>();
        int photoCount = data.getCount();
        if (photoCount > 0) {
            data.moveToFirst();
            do {
                String photoPath = data.getString(data.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
                String addDate = data.getString(data.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_ADDED));
                String name = data.getString(data.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME));
                ImageInfo imageInfo = new ImageInfo();
                imageInfo.setImagePath(photoPath);
                imageInfo.setAddDate(addDate);
                wholeImages.add(imageInfo);
            } while (data.moveToNext());
        }
        //调用接口
        loadFinishCallback.wholeImage(wholeImages);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
    private LoadFinishCallback loadFinishCallback;

    public void setLoadFinishCallback(LoadFinishCallback loadFinishCallback) {
        this.loadFinishCallback = loadFinishCallback;
    }

    public interface LoadFinishCallback {
        void wholeImage(List<ImageInfo> imageWhole);
    }
}