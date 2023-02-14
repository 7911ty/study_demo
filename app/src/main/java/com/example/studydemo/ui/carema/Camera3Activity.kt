package com.example.studydemo.ui.carema

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.base.ui.BaseActivity
import com.example.studydemo.R
import okio.FileNotFoundException
import okio.IOException
import java.io.File

/**
 * 拉起相机拍照并存储展示
 */
class Camera3Activity : BaseActivity() {
    private val CAMERA_REQUEST_CODE = 1121
    private lateinit var photoUri: Uri
    private lateinit var image: ImageView
    private lateinit var imagePath: String
    private lateinit var imageName: String

    override fun initLayout(): Int {
        return R.layout.activity_camera3
    }

    override fun initView() {
        image = findViewById<ImageView>(R.id.camera_iv)
        val camera_bt = findViewById<Button>(R.id.bt_camera)
        camera_bt.setOnClickListener { view: View? ->
            // 新建一个文件，他的位置位于外存根目录下，且文件名为outputimage.jpg
            imageName = "outputimage.jpg"
            val file = File(getExternalFilesDir(null), imageName)
            // 为避免输出流错误，需要捕获错误
            try {
                file.createNewFile()
                imagePath =  file.absolutePath
            } catch (e: IOException) {
                e.printStackTrace()
            }
            // 使用FileProvider.getUriForFile获取相片存储的URI
            // 参数一：上下文
            // 参数二：我们之前在androidmanifest中注册provider时自定义的authorities
            // 参数三：欲转换成uri路径的文件
            photoUri = FileProvider.getUriForFile(this, "com.example.studydemo.fileprovider", file)
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.CAMERA),
                    CAMERA_REQUEST_CODE
                )
            } else {
                startCamera()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            CAMERA_REQUEST_CODE -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "onRequestPermissionsResult: ")
                startCamera()
            } else {
                Toast.makeText(this, "you denied the permission", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun startCamera() {
        // 使用隐式intent唤醒相机
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        // 传入photouri，以保证我们的照片存储到指定位置
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
        startActivityForResult(intent, CAMERA_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(TAG, "onActivityResult: ")
        when (requestCode) {
            CAMERA_REQUEST_CODE -> {
                Log.d(TAG, "onActivityResult: 拍照完成")
                MediaStore.Images.Media.insertImage(this.contentResolver,
                   imagePath, imageName, null);
                if (resultCode == RESULT_OK) {
                    try {
                        // 使用bitmap解码文件
                        val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(photoUri))
                        // 将图片展示在imageview上面
                        image.setImageBitmap(bitmap)
                    } catch (e: FileNotFoundException) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }
}