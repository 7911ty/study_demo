package com.example.studydemo.ui.okhttp

import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.studydemo.R
import com.example.studydemo.base.BaseActivity
import kotlinx.android.synthetic.main.activity_my_web_view.*

class MyWebViewActivity : BaseActivity() {

    override fun onBackPressed() {
        if (web_view.canGoBack()){
            web_view.goBack()
        }else{
            super.onBackPressed()
        }
    }

    override fun initLayout(): Int {
        return R.layout.activity_my_web_view
    }

    override fun initView() {
        web_view.webViewClient = MyWebViewClient()
        web_view.loadUrl("https://www.baidu.com")
        // 是否可运行 JavaScript 脚本
        web_view.settings.javaScriptEnabled = true
        //是否可用Javascript(window.open)打开窗口
        // 参考 ： https://blog.csdn.net/weixin_35691921/article/details/104558268
        web_view.settings.javaScriptCanOpenWindowsAutomatically = true
    }

    class MyWebViewClient : WebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            Log.d("MyWebViewClient", "onPageFinished: ")
        }
    }
}