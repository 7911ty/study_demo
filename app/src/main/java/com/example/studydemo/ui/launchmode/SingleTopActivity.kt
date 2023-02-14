package com.example.studydemo.ui.launchmode

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.studydemo.R

class SingleTopActivity : AppCompatActivity() , View.OnClickListener{
    private  val TAG = "SingleTopActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_top)
        Log.d(TAG, "onCreate: ")
        initView()
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: ")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.d(TAG, "onNewIntent: ")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
    }


    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }
    private fun initView() {
        val mode_bt_1 = findViewById<Button>(R.id.mode_bt_1).setOnClickListener(this)
        val mode_bt_2 = findViewById<Button>(R.id.mode_bt_2).setOnClickListener(this)
        val mode_bt_3 = findViewById<Button>(R.id.mode_bt_3).setOnClickListener(this)
        val mode_bt_4 = findViewById<Button>(R.id.mode_bt_4).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.mode_bt_1 -> {
                startActivity(Intent(SingleTopActivity@ this, SingleInstanceActivity::class.java))
            }
            R.id.mode_bt_2 -> {
                startActivity(Intent(SingleTopActivity@ this, SingleTopActivity::class.java))
            }
            R.id.mode_bt_3 -> {
                startActivity(Intent(SingleTopActivity@ this, SingleTaskActivity::class.java))
            }
            R.id.mode_bt_4 -> {
                startActivity(Intent(SingleTopActivity@ this, StandardActivity::class.java))
            }
        }
    }
}