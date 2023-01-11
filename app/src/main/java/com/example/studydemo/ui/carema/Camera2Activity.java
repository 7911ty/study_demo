package com.example.studydemo.ui.carema;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.base.ui.BaseActivity;
import com.example.studydemo.R;
import com.example.studydemo.bean.ImageInfo;
import com.example.studydemo.ui.carema.adapter.AlbumImageAdapter;
import com.example.studydemo.ui.carema.loader.PhotoLoader;

import java.util.ArrayList;
import java.util.List;

public class Camera2Activity extends BaseActivity {

    private ImageView camera_iv;
    private RecyclerView album_image_rv;
    private List<ImageInfo> imageInfos;
    private AlbumImageAdapter albumImageAdapter;

    @Override
    protected int initLayout() {
        return R.layout.activity_camera2;
    }

    @Override
    protected void initView() {
        Button camera_bt = findViewById(R.id.camera_bt);
        Button album_bt = findViewById(R.id.album_bt);
        camera_iv = findViewById(R.id.camera_iv);
        album_image_rv = findViewById(R.id.album_image_rv);
        album_image_rv.setLayoutManager(new LinearLayoutManager(this));
        camera_bt.setOnClickListener(view -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 1121);
        });
        imageInfos = new ArrayList<>();
        new PhotoLoader(this).setLoadFinishCallback(imageWhole -> {
            imageInfos = imageWhole;
            for (int i = 0; i < imageWhole.size(); i++) {
                ImageInfo imageInfo = imageWhole.get(i);
                String imagePath = imageInfo.getImagePath();
                Log.d(TAG, "wholeImage: imagePath. " + imagePath);
            }
            albumImageAdapter = new AlbumImageAdapter(Camera2Activity.this, imageInfos);
            album_image_rv.setAdapter(albumImageAdapter);
        });

    }

    public static final int CHOOSE_PHOTO = 2;

    //打开相册
    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    Toast.makeText(this, "you denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1121 && data != null) {
            Object data1 = data.getExtras().get("data");
            if (data1 instanceof Bitmap) {
                camera_iv.setImageBitmap((Bitmap) data1);
            }
        }
        switch (requestCode) {
            case 1121:
                if (data != null) {
                    Object data1 = data.getExtras().get("data");
                    if (data1 instanceof Bitmap) {
                        camera_iv.setImageBitmap((Bitmap) data1);
                    }
                }
                break;
            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    if (Build.VERSION.SDK_INT >= 19) {  //4.4及以上的系统使用这个方法处理图片；
                        handleImageOnKitKat(data);
                    } else {
                        handleImageBeforeKitKat(data);  //4.4及以下的系统使用这个方法处理图片
                    }
                }
            default:
                break;
        }
    }

    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        //通过Uri和selection来获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            camera_iv.setImageBitmap(bitmap);
        } else {
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 4.4及以上的系统使用这个方法处理图片
     *
     * @param data
     */
    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this, uri)) {
            //如果document类型的Uri,则通过document来处理
            String docID = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docID.split(":")[1];     //解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;

                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/piblic_downloads"), Long.valueOf(docID));

                imagePath = getImagePath(contentUri, null);

            }

        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            //如果是content类型的uri，则使用普通方式使用
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            //如果是file类型的uri，直接获取路径即可
            imagePath = uri.getPath();

        }

        displayImage(imagePath);
    }
}