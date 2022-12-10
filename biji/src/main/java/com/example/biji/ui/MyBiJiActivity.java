package com.example.biji.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.base.ui.BaseActivity;
import com.example.base.view.MyTitleView;
import com.example.biji.R;
import com.example.biji.adapter.NoteAdapter;
import com.example.biji.beans.Note;
import com.example.biji.dp.CRUD;
import com.example.biji.dp.NoteDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyBiJiActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    private static final String TAG = "MainActivity";
    private NoteDatabase dbHelper;
    private FloatingActionButton button;
    private RecyclerView noteRecyclerView;
    private NoteAdapter adapter;
    private List<Note> noteList = new ArrayList<Note>();
    public Intent intent = new Intent();

    @Override
    protected int initLayout() {
        return R.layout.activity_biji;
    }

    @Override
    protected void initView() {
        button = findViewById(R.id.add_fab);
        initRecyclerView();
        button.setOnClickListener(v -> {
            Intent intent = new Intent(MyBiJiActivity.this, NoteEditActivity.class);
            intent.putExtra("mode", 4);//新创建的笔记mode为4
            //使用这个方法可以接受intent返回的数据
            startActivityForResult(intent, 0);
        });
        initTitleBar();
    }

    private void initRecyclerView() {
        noteRecyclerView = findViewById(R.id.rv_note);
        adapter = new NoteAdapter(getApplicationContext(), noteList);
        refreshListView();
        noteRecyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        noteRecyclerView.setAdapter(adapter);
        adapter.setItemClickListener((view, position) -> {
            List<Note> noteList = adapter.getNoteList();
            Note note = noteList.get(position);
            Intent intent = new Intent(this, NoteEditActivity.class);
            intent.putExtra("content", note.getContent());
            intent.putExtra("id", note.getId());
            intent.putExtra("time", note.getTime());
            intent.putExtra("mode", 3);//已经有的笔记，再次编辑传入mode为3
            intent.putExtra("tag", note.getTag());
            startActivityForResult(intent, 1); 
            Log.d(TAG, "initView: view = " + view.toString() + " , position = " + position);
        });

        //为RecycleView绑定触摸事件
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                //首先回调的方法 返回int表示是否监听该方向
                int dragFlags = ItemTouchHelper.UP|ItemTouchHelper.DOWN;//拖拽
                int swipeFlags = ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT;//侧滑删除
                return makeMovementFlags(dragFlags,swipeFlags);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                Log.d(TAG, "onMove: noteList1 = " + noteList);
                //滑动事件
                Collections.swap(noteList,viewHolder.getAdapterPosition(),target.getAdapterPosition());
                adapter.notifyItemMoved(viewHolder.getAdapterPosition(),target.getAdapterPosition());
                Log.d(TAG, "onMove: noteList2 = " + noteList);
                Note oldNote = noteList.get(viewHolder.getAdapterPosition());
                Note targetNote = noteList.get(target.getAdapterPosition());
                long oldId = oldNote.getId();
                oldNote.setId(targetNote.getId());
                targetNote.setId(oldId);
                CRUD op = new CRUD(context);
                op.open();
                op.updateNote(oldNote);
                op.updateNote(targetNote);
                op.close();
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                //侧滑事件
//                list.remove(viewHolder.getAdapterPosition());
                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }

            @Override
            public boolean isLongPressDragEnabled() {
                //是否可拖拽
                return true;
            }
        });
        helper.attachToRecyclerView(noteRecyclerView);
    }

    private void initTitleBar() {
        myTitleView.setTitleClickListener(new MyTitleView.TitleClickListener() {
            @Override
            public void right1Click() {
                Intent intent = new Intent(MyBiJiActivity.this, SearchNoteActivity.class);
                startActivity(intent);
            }

            @Override
            public void right2Click() {
                showDelegateDialog();
            }
        });
    }

    //接受startActivityForResult的结果
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (data == null) {
            return;
        }
        Bundle extras = data.getExtras();
        if (extras == null) {
            return;
        }
        int returnMode = extras.getInt("mode", -1);
        long nodeId = extras.getLong("id", 0);
        if (returnMode == 1) {
            //更新笔记
            String content = extras.getString("content");
            String time = extras.getString("time");
            int tag = extras.getInt("tag", 1);

            Note newNote = new Note(content, time, tag);
            newNote.setId(nodeId);
            CRUD op = new CRUD(this);
            op.open();
            op.updateNote(newNote);
            op.close();
        } else if (returnMode == 0) {
            //新建笔记
            String content = extras.getString("content");
            String time = extras.getString("time");
            int tag = extras.getInt("tag", 1);

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
        if (noteList.size() > 0) {
            noteList.clear();
        }
        List<Note> allNotes = op.getAllNotes();
        noteList.addAll(allNotes);
        Log.d(TAG, "refreshListView: allNotes = " + allNotes);
        op.close();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.rv_note) {
            Note note = (Note) parent.getItemAtPosition(position);
            Intent intent = new Intent(this, NoteEditActivity.class);
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
            showDelegateDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    private void showDelegateDialog() {
        new AlertDialog.Builder(MyBiJiActivity.this)
                .setMessage("确定删除所有笔记？")
                .setPositiveButton("确定", (dialog, which) -> {
                    //删除所有笔记
                    CRUD op = new CRUD(MyBiJiActivity.this);
                    op.open();
                    op.removeAllNote();
                    op.close();
                    refreshListView();
                })
                .setNegativeButton("取消", (dialog, which) -> dialog.dismiss())
                .create().show();
    }
}