package com.example.studydemo.ui.listview;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studydemo.R;

public class MyListViewActivity extends AppCompatActivity {
    private ListView mLV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        mLV = findViewById(R.id.listview_lv1);
        mLV.setAdapter(new ListViewAdapter(MyListViewActivity.this));


        System.out.println("1111");

    }
}

