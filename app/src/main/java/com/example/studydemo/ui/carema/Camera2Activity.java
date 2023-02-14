package com.example.studydemo.ui.carema;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.CallLog;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.base.ui.BaseActivity;
import com.example.studydemo.R;
import com.example.studydemo.bean.album.ImageInfo;
import com.example.studydemo.ui.carema.adapter.AlbumImageAdapter;

import java.util.ArrayList;
import java.util.List;

public class Camera2Activity extends BaseActivity {
    private static final String TAG = "Camera2Activity";
    private ImageView camera_iv;
    private RecyclerView album_image_rv;
    private List<ImageInfo> imageInfos;
    // 图片地址
    private List<String> imageUrls;
    private AlbumImageAdapter albumImageAdapter;
    private final static int CAMERA_REQUEST_CODE = 1121;

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
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "initView: ");
                ActivityCompat.requestPermissions(this, 
                        new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
            } else {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA_REQUEST_CODE);
            }
        });
        album_bt.setOnClickListener(view -> {
//            getPhotosInfo();
//            getPageNameList();
            initColumData();
        });
        imageInfos = new ArrayList<>();
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
            case CAMERA_REQUEST_CODE:
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
        Log.d(TAG, "onActivityResult: ");
        Log.d(TAG, "onActivityResult: requestCode = " + requestCode);
        Log.d(TAG, "onActivityResult: resultCode = " + resultCode);
        switch (requestCode) {
            case CAMERA_REQUEST_CODE:
                Log.d(TAG, "onActivityResult: 拍照完成");
                break;
        }
    }

    private void initColumData() {


        String[] columns = {MediaStore.Images.Media._ID, MediaStore.Images.Thumbnails.DATA, MediaStore.Images.Media.DATA, MediaStore.Images.Media.BUCKET_ID,

                MediaStore.Images.Media.BUCKET_DISPLAY_NAME};

        String selection = "0==0) GROUP BY (bucket_id";
       final String IMAGE_SELECTION_TYPE =
                MediaStore.Files.FileColumns.MEDIA_TYPE + "=?" + " AND " + MediaStore.MediaColumns.SIZE + ">0";
      final String ALL_SELECTION_TYPE = "(" + MediaStore.Files.FileColumns.MEDIA_TYPE + "=? OR " + MediaStore.Files.FileColumns.MEDIA_TYPE + "=?)" +
                " AND " + MediaStore.MediaColumns.SIZE + ">0";
        String sortOrder = MediaStore.Images.Media.DATE_MODIFIED;

//        Cursor cursor = this.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, selection, null, sortOrder);
        ContentResolver contentResolver = getContentResolver();
        final String orderBy = MediaStore.Images.Media.DATE_MODIFIED;
        String[] photoColumns = new String[]{
                MediaStore.Images.Media.BUCKET_ID, //
                MediaStore.Images.Media.DATE_ADDED, //
        };
     final String[] IMAGE_WHERE_TYPE = new String[]{String.valueOf(MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE)};

        final String[] IMAGE_PROJECTION = {
                MediaStore.Files.FileColumns._ID,
                MediaStore.Files.FileColumns.DATA,
                MediaStore.Files.FileColumns.WIDTH,
                MediaStore.Files.FileColumns.HEIGHT,
                MediaStore.Files.FileColumns.SIZE,
                MediaStore.Files.FileColumns.MEDIA_TYPE,
                MediaStore.Files.FileColumns.DISPLAY_NAME};
//        String selection = "0=0) group by (" + MediaStore.Images.Media.BUCKET_ID;
//        String selection = "0==0) GROUP BY (" + MediaStore.Images.Media.BUCKET_ID;

        Cursor imagecursor = contentResolver
                .query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_PROJECTION,
                        IMAGE_SELECTION_TYPE, IMAGE_WHERE_TYPE, orderBy + " DESC");
        for (int i = 0; i < imagecursor.getCount(); i++) {
            imagecursor.moveToPosition(i);
            int bucket_id_index = imagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_ID);
            int dateAdd = imagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_ADDED);
            int count = imagecursor.getCount();
            int bucket_display_name_index = imagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
            String displayName = imagecursor.getString(bucket_display_name_index);
            String bucketiId = imagecursor.getString(bucket_id_index);
            String date = imagecursor.getString(dateAdd);
            ImageInfo imageInfo = new ImageInfo();
            imageInfo.setBucketId(bucketiId);
            imageInfo.setAddDate(date);
            imageInfo.setBucketName(displayName);
            Log.i(TAG, "imageInfo = " + imageInfo.toString());
            Log.i(TAG, "count = " +count);
        }
    }

    //获取设备上所有的照片信息
    private void getPhotosInfo() {
        ContentResolver contentResolver = getContentResolver();
        final String orderBy = MediaStore.Images.Media.DATE_MODIFIED;
        String[] photoColumns = new String[]{
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.TITLE,
                MediaStore.Images.Media.MIME_TYPE,
                MediaStore.Images.Media.SIZE,
                MediaStore.Images.Media.ORIENTATION,
                MediaStore.Images.Media.DATE_MODIFIED, // 日期_修改
                MediaStore.Images.Media.DATE_ADDED, //
                MediaStore.Images.Media.DISPLAY_NAME, // 显示器名称
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME, //

        };
//        String selection = "0=0) group by (" + MediaStore.Images.Media.BUCKET_ID;
//	      两种方法均可		
//			Cursor cursor=
//		    this.managedQuery(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, mediaColumns, null, null, null);
        Cursor cursor = contentResolver.query
                (MediaStore.Images.Media.EXTERNAL_CONTENT_URI, photoColumns, null, null,
                        orderBy + " DESC");
        while (cursor.moveToNext()) {
            String _id =
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID));
            String filePath =
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
            String title =
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.TITLE));
            String mime_type =
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.MIME_TYPE));
            String size =
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE));

            String date_modified =
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_MODIFIED));
            String display_name =
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME));
            String date_added =
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_ADDED));
            String bucket_display_name =
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME));
            //得到照片旋转角度方法一
            String orientation0 = cursor.getString
                    (cursor.getColumnIndexOrThrow(MediaStore.Images.Media.ORIENTATION));
            Log.i(TAG, "_id=" + _id);
            Log.i(TAG, "size=" + size);
            Log.i(TAG, "title=" + title);
            Log.i(TAG, "filePath=" + filePath);
            Log.i(TAG, "mime_type=" + mime_type);
            Log.i(TAG, "第一处 orientation0=" + orientation0);
            Log.i(TAG, "date_modified = " + date_modified);
            Log.i(TAG, "display_name = " + display_name);
            Log.i(TAG, "bucket_display_name = " + bucket_display_name);
            try {
                ExifInterface exifInterface = new ExifInterface(filePath);
                String image_length =
                        exifInterface.getAttribute(ExifInterface.TAG_IMAGE_LENGTH);
                String image_width =
                        exifInterface.getAttribute(ExifInterface.TAG_IMAGE_WIDTH);
                String orientation1 =
                        exifInterface.getAttribute(ExifInterface.TAG_ORIENTATION);
                String dateTime =
                        exifInterface.getAttribute(ExifInterface.TAG_DATETIME);
                ImageInfo imageInfo = new ImageInfo();
                imageInfo.setHeight(image_length);
                imageInfo.setImagePath(filePath);
                imageInfo.setSize(size);
                imageInfo.setTitle(title);
                imageInfo.setAddDate(date_added);
                imageInfos.add(imageInfo);
                Log.i(TAG, "image_length=" + image_length);
                Log.i(TAG, "image_width=" + image_width);
                Log.i(TAG, "dateTime=" + dateTime);
                //得到照片旋转角度方法二
                //应该结合ExifInterface源码分析.
                //此处有待于进一步分析和验证
                switch (Integer.parseInt(orientation1)) {
                    case 1:
                        Log.i(TAG, "第二处旋转角度=" + 0);
                        break;
                    case 2:
                        //matrix.invert(matrix);
                        break;
                    case 3:
                    case 4:
                        //matrix.invert(matrix);
                        //matrix.setRotate(180);
                        Log.i(TAG, "第二处旋转角度=" + 180);
                        break;
                    case 5:
                    case 6:
                    case 7:
                        //matrix.setRotate(90);
                        //matrix.invert(matrix);
                        Log.i(TAG, "第二处旋转角度=" + 90);
                        break;
                    case 8:
                        //matrix.setRotate(270);
                        Log.i(TAG, "第二处旋转角度=" + 270);
                        break;
                    default:
                        break;
                }
                Log.i(TAG, "XXXXXXXXXXXXXXXXXXX  查询结束");
            } catch (Exception e) {
                 Log.d(TAG, "getPhotosInfo: e = " + e.getMessage());
            }
        }
    }

    //获取设备上所有的照片信息
    private void getPageNameList() {
        ContentResolver contentResolver = getContentResolver();
        final String[] CALL_LOG_PROJECTION = new String[] {
                CallLog.Calls._ID,
                CallLog.Calls.NUMBER,
                CallLog.Calls.DATE,
                CallLog.Calls.DURATION,
                CallLog.Calls.TYPE,
                CallLog.Calls.CACHED_NAME,
                CallLog.Calls.CACHED_NUMBER_TYPE,
                CallLog.Calls.CACHED_NUMBER_LABEL,
               };
        String selection = "0==0) GROUP BY (" + CallLog.Calls.NUMBER;
        final int ID_COLUMN_INDEX = 0;
        final int NUMBER_COLUMN_INDEX = 1;
        final int DATE_COLUMN_INDEX = 2;
        final int DURATION_COLUMN_INDEX = 3;
        final int CALL_TYPE_COLUMN_INDEX = 4;
        final int CALLER_NAME_COLUMN_INDEX = 5;
        final int CALLER_NUMBERTYPE_COLUMN_INDEX = 6;
        final int CALLER_NUMBERLABEL_COLUMN_INDEX = 7;
        final int THIS_CALLLOG_COUNT = 8;
        Cursor cursor  = contentResolver.query(CallLog.Calls.CONTENT_URI, CALL_LOG_PROJECTION,selection, null, CallLog.Calls.DEFAULT_SORT_ORDER
                + " limit "+10);
        while (cursor.moveToNext()) {
            String _id =
                    cursor.getString(cursor.getColumnIndexOrThrow(CallLog.Calls._ID));
            String number =
                    cursor.getString(cursor.getColumnIndexOrThrow( CallLog.Calls.NUMBER));
            String date = cursor.getString(cursor.getColumnIndexOrThrow( CallLog.Calls.DATE));
            String duration = cursor.getString(cursor.getColumnIndexOrThrow(  CallLog.Calls.DURATION));
            String type = cursor.getString(cursor.getColumnIndexOrThrow( CallLog.Calls.TYPE));
            String cached_name = cursor.getString(cursor.getColumnIndexOrThrow( CallLog.Calls.CACHED_NAME));

            Log.i(TAG, "_id = " + _id);
            Log.i(TAG, "number = " + number);
            Log.i(TAG, "date = " + date);
            Log.i(TAG, "第一处 duration = " + duration);
            Log.i(TAG, "type = " + type);
            Log.i(TAG, "cached_name = " + cached_name);
        }
    }
}