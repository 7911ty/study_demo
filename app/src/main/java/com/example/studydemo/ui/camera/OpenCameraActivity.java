package com.example.studydemo.ui.camera;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studydemo.R;

public class OpenCameraActivity extends AppCompatActivity {

    private ImageView IMG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_camera);

        IMG = findViewById(R.id.IMG);
        //设置裁剪比例
        CameraActivity.setClipRatio(1, 1);

        // setOutputFormat()  设置图片输出格式
        // setClipRatio()  设置裁剪比例
        // setClipPixel()  设置裁剪像素
        // setScales()  裁剪时是否可以缩放
        // setNoFaceDetections()  是否检测人脸
    }

    //从相机获取图片
    public void TestBtn01(View view) {
        startActivity(new Intent(OpenCameraActivity.this, CameraActivity.class).putExtra(CameraActivity.ExtraType, CameraActivity.CAMERA));
    }

    //从相册获取图片
    public void TestBtn02(View view) {
        startActivity(new Intent(OpenCameraActivity.this, CameraActivity.class).putExtra(CameraActivity.ExtraType, CameraActivity.PHOTO));
    }

    @Override
    protected void onResume() {
        super.onResume();
        //获得相册、相机返回的结果，并显示
        if (CameraActivity.LISTENING) {
            Log.e("TAG", "返回的Uri结果：" + CameraActivity.IMG_URI);
            Log.e("TAG", "返回的File结果：" + CameraActivity.IMG_File.getPath());
            CameraActivity.LISTENING = false;   //关闭获取结果
            IMG.setImageURI(CameraActivity.IMG_URI);  //显示图片到控件
        }
    }
}