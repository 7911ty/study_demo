package com.example.studydemo.ui.okhttp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.studydemo.R
import kotlinx.android.synthetic.main.activity_network.*

class NetworkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network)

        webview_bt.setOnClickListener {
            intent = Intent(this, MyWebViewActivity::class.java)
            startActivity(intent)
        }
        okhttp_bt.setOnClickListener {
            intent = Intent(this, OkhttpActivity::class.java)
            startActivity(intent)
        }
    }
}