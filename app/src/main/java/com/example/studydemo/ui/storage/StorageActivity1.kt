package com.example.studydemo.ui.storage

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.studydemo.R
import com.example.studydemo.base.BaseActivity
import com.example.studydemo.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_storage1.*
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class StorageActivity1 : BaseActivity(),View.OnClickListener {

    override fun initLayout(): Int {
        return R.layout.activity_storage1
    }

    override fun initView() {
        StatusBarUtil.setTranslucentStatus(this)
        StatusBarUtil.setAndroidNativeLightStatusBar(this, true)
        val saveButton = findViewById<Button>(R.id.save_bt)
        val read_bt = findViewById<Button>(R.id.read_bt)
        val textView = findViewById<TextView>(R.id.show_text_tv)
        val string = show_text_ed.text.toString()
        Log.d(TAG, "initView: string = $string")
        saveButton.setOnClickListener {
            save(string)
        }
        read_bt.setOnClickListener {
            val readText = readText()
            textView.text = readText
        }
    }

    override fun initData() {
    }

    private fun readText():String {
        val stringBuilder = StringBuilder()
        try {
            val openFileInput = openFileInput("data.txt")
            val bufferedReader = BufferedReader(InputStreamReader(openFileInput))
            bufferedReader.use {
                bufferedReader.forEachLine {
                    stringBuilder.append(it)
                }
            }
        } catch (e: Exception) {
            Log.d(TAG, "read: e = " + e.message)
        }
        return stringBuilder.toString()
    }

    private fun save(string: String) {
        try {
            val openFileOutput = openFileOutput("data.txt", Context.MODE_PRIVATE)
            val writer = BufferedWriter(OutputStreamWriter(openFileOutput))
            writer.use {
                it.write(string)
            }
        } catch (e: Exception) {
            Log.d(TAG, "save: e = " + e.message)
        }
    }

    override fun onClick(p0: View?) {

    }
}