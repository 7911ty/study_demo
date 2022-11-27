package com.example.studydemo.ui.coordinatorlayout

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.studydemo.R
import kotlinx.android.synthetic.main.activity_my_coordinator_layout.*

class MyCoordinatorLayoutActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_coordinator_layout)
        behavior_bt.setOnClickListener(this)
        coordinatorlayout_bt.setOnClickListener(this)
        appbar_scroll.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        if (view?.id == R.id.behavior_bt) {
            val intent = Intent(this, BehaviorStudyActivity::class.java)
            startActivity(intent)
        } else if (view?.id == R.id.coordinatorlayout_bt) {
            val intent = Intent(this, CoordinatorLayoutActivity::class.java)
            startActivity(intent)
        }else if (view?.id == R.id.appbar_scroll) {
            val intent = Intent(this, AppbarAndScrollActivity::class.java)
            startActivity(intent)
        }
    }
}