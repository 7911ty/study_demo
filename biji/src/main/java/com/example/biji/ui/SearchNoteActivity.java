package com.example.biji.ui;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biji.R;
import com.example.biji.adapter.NoteAdapter;
import com.example.biji.beans.Note;
import com.example.biji.dp.CRUD;

import java.util.ArrayList;

public class SearchNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_note);

        ImageView imageView = findViewById(R.id.iv_search);
        EditText editText = findViewById(R.id.et_search);
        RecyclerView noteRecyclerView = findViewById(R.id.rv_note_search);
        noteRecyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        NoteAdapter noteAdapter = new NoteAdapter(this, new ArrayList<>());
        noteRecyclerView.setAdapter(noteAdapter);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Editable text = editText.getText();
                CRUD op = new CRUD(SearchNoteActivity.this);
                op.open();
                // set adapter
                ArrayList<Note> noteList = new ArrayList<>();
                noteList.addAll(op.queryNoteByContent(text.toString()));
                op.close();
                noteAdapter.setNoteList(noteList);
            }
        });
    }
}