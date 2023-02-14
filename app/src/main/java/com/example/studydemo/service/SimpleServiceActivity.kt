package com.example.studydemo.service

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.studydemo.R

class SimpleServiceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_service)
        initView()
    }

    private fun initView() {
        val intent = Intent(this,SimpleService::class.java)
        findViewById<Button>(R.id.start_service).setOnClickListener {
            startService(intent)
        }
        findViewById<Button>(R.id.end_service).setOnClickListener {
            stopService(intent)
        }
    }
}