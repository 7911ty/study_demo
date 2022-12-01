package com.example.biji;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MyBiJiActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    private static final String TAG = "MainActivity";
    private NoteDatabase dbHelper;

    private FloatingActionButton button;
    private TextView tv;
    private ListView lv;
    private Toolbar mToolbar;

    private NoteAdapter adapter;
    private List<Note> noteList = new ArrayList<Note>();
    public Intent intent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biji);
        button = findViewById(R.id.add_fab);
        mToolbar = findViewById(R.id.title_tb);
        //设置标题栏
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//设置toolbar代替actionbar
        mToolbar.setNavigationIcon(R.drawable.ic_baseline_menu_24);
        // tv = findViewById(R.id.tv);
        lv = findViewById(R.id.lv);
        adapter = new NoteAdapter(getApplicationContext(), noteList);
        refreshListView();
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(MyBiJiActivity.this, EditActivity.class);
            intent.putExtra("mode", 4);//新创建的笔记mode为4
            //使用这个方法可以接受intent返回的数据
            startActivityForResult(intent, 0);
        });
        mToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareDialog shareDialog = new ShareDialog(MyBiJiActivity.this);
                shareDialog.show();
            }
        });
    }

    //接受startActivityForResult的结果
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        int returnMode = data.getExtras().getInt("mode", -1);
        long nodeId = data.getExtras().getLong("id", 0);
        if (returnMode == 1) {
            //更新笔记
            String content = data.getExtras().getString("content");
            String time = data.getExtras().getString("time");
            int tag = data.getExtras().getInt("tag", 1);

            Note newNote = new Note(content, time, tag);
            newNote.setId(nodeId);
            CRUD op = new CRUD(this);
            op.open();
            op.updateNote(newNote);
            op.close();
        } else if (returnMode == 0) {
            //新建笔记
            String content = data.getExtras().getString("content");
            String time = data.getExtras().getString("time");
            int tag = data.getExtras().getInt("tag", 1);

            Note newNote = new Note(content, time, tag);
            CRUD op = new CRUD(this);
            op.open();
            op.addNote(newNote);
            op.close();
        } else if (returnMode == 2) {
            //删除笔记
            Note newNote = new Note();
            newNote.setId(nodeId);
            CRUD op = new CRUD(this);
            op.open();
            op.removeNote(newNote);
            op.close();
        }
        refreshListView();
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void refreshListView() {
        CRUD op = new CRUD(this);
        op.open();
        // set adapter
        if (noteList.size() > 0) noteList.clear();
        noteList.addAll(op.getAllNotes());
        op.close();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.lv) {
            Note note = (Note) parent.getItemAtPosition(position);
            Intent intent = new Intent(this, EditActivity.class);
            intent.putExtra("content", note.getContent());
            intent.putExtra("id", note.getId());
            intent.putExtra("time", note.getTime());
            intent.putExtra("mode", 3);//已经有的笔记，再次编辑传入mode为3
            intent.putExtra("tag", note.getTag());
            startActivityForResult(intent, 1);
        }
    }

    //创建删除菜单
    @Override
    public boolean onCreatePanelMenu(int featureId, @NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreatePanelMenu(featureId, menu);
    }

    //点击删除菜单时
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_clear) {//deleteEdit(id);
            new AlertDialog.Builder(MyBiJiActivity.this)
                    .setMessage("确定删除所有笔记？")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //删除所有笔记
                            CRUD op = new CRUD(MyBiJiActivity.this);
                            op.open();
                            op.removeAllNote();
                            op.close();
                            refreshListView();
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        }

        return super.onOptionsItemSelected(item);
    }

}