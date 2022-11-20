package com.example.studydemo.ui.okhttp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.studydemo.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkhttpActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "OkhttpActivity";
    private static final int GET = 1;
    private static final int POST = 1;
    private Button button_get, button_post;
    private TextView text_okhttp;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case GET:
                    Log.d(TAG, "handleMessage: msg.obj = " + msg.obj);
                    text_okhttp.setText((String) msg.obj);

                default:
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        button_get = findViewById(R.id.button_get);
        button_post = findViewById(R.id.button_post);
        text_okhttp = findViewById(R.id.text_okhttp);
        button_get.setOnClickListener(this);
        button_post.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_get:
                Log.d(TAG, "onClick: get请求");
//                get("http://192.168.1.108:8080/myWeb/book/allbook", new Callback() {
                get("www.baidu.com", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        Log.d(TAG, "onFailure: e = " + e.getMessage());
                        Log.d(TAG, "onFailure: e = " + e.getLocalizedMessage());
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        Log.d(TAG, "onResponse: ");
                        Log.d(TAG, "response: "+ response.body().string());
                    }
                });
                break;
            case R.id.button_post:
                break;
            default:
                break;
        }
    }

    private void get(String url,Callback callback) {
        Log.d(TAG, "get: url = " + url);
        new OkHttpClient();
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @NonNull
                    @Override
                    public Response intercept(@NonNull Chain chain) throws IOException {
                        return chain.proceed(chain.request());
                    }
                })
                .build();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
