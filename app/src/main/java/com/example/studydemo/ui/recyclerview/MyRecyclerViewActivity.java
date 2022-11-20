package com.example.studydemo.ui.recyclerview;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studydemo.R;

public class MyRecyclerViewActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyRecyclerAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        adapter = new MyRecyclerAdapter(MyRecyclerViewActivity.this);
        recyclerView = findViewById(R.id.recyclerview_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(MyRecyclerViewActivity.this));
        recyclerView.setAdapter(adapter);
    }
}
