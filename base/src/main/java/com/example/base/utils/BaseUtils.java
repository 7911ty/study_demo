package com.example.base.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class BaseUtils {
    /**
     * todo 将网络资源图片转换为Bitmap
     * @param imgUrl 网络资源图片路径
     * @return Bitmap
     * 该方法调用时要放在子线程中
     */
    public static Bitmap netToLoacalBitmap(String imgUrl){
        Bitmap bitmap = null;
        InputStream in=null;
        BufferedOutputStream out = null;
        try{
            in = new BufferedInputStream(new URL(imgUrl).openStream(),1024);
            final ByteArrayOutputStream dataStream = new ByteArrayOutputStream();
            out = new BufferedOutputStream(dataStream,1024);
            copy(in,out);
            out.flush();
            byte[] data = dataStream.toByteArray();
            bitmap = BitmapFactory.decodeByteArray(data,0,data.length);
            data = null;
            return bitmap;
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    private static void copy(InputStream in, OutputStream out)
            throws IOException {
        byte[] b = new byte[1024];
        int read;
        while ((read = in.read(b)) != -1) {
            out.write(b, 0, read);
        }
    }

    // 获取当前时间
    public static String getTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss"); //设置时间格式
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+08")); //设置时区
        Date curDate = new Date(System.currentTimeMillis()); //获取当前时间
        String createDate = formatter.format(curDate);   //格式转换
        return createDate;
    }
}
