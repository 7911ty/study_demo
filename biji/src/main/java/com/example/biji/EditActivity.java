package com.example.biji;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EditActivity extends BaseActivity {
    private static final String TAG = "EditActivity";

    private EditText editText;

    private String old_content = "";
    private String old_time = "";
    private int old_tag = 1;
    private long id = 0;
    private int openMode = 0;
    private int tag = 1;
    public Intent intent = new Intent();
    //以后判断标签是否改变
    private boolean tagChange = false;

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_layout);

        mToolbar = findViewById(R.id.edit_title_tb);
        //设置标题栏
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//设置toolbar代替actionbar
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoSetMassage();
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        editText = findViewById(R.id.biji_et);
        Intent getIntent = getIntent();

        openMode = getIntent.getIntExtra("mode", 0);
        if (openMode == 3) {
            id = getIntent.getLongExtra("id", 0);
            old_content = getIntent.getStringExtra("content");
            old_time = getIntent.getStringExtra("time");
            old_tag = getIntent.getIntExtra("tag", 1);
            editText.setText(old_content);
            editText.setSelection(old_content.length());
        }
    }

    //创建删除菜单
    @Override
    public boolean onCreatePanelMenu(int featureId, @NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.edit_menu, menu);
        return super.onCreatePanelMenu(featureId, menu);
    }

    //点击删除菜单时
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.delete) {//deleteEdit(id);
            new AlertDialog.Builder(EditActivity.this)
                    .setMessage("确定删除？")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (openMode == 4) {
                                //新打开的edit
                                intent.putExtra("mode", "-1");
                            } else {
                                intent.putExtra("mode", 2);//删除笔记
                                intent.putExtra("id", id);
                            }
                            setResult(RESULT_OK, intent);
                            finish();
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

    //点击屏幕的返回键
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_HOME) {
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_BACK) {
            autoSetMassage();
            setResult(RESULT_OK, intent);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void autoSetMassage() {
        if (openMode == 4) {
            if (editText.getText().toString().length() == 0) {
                intent.putExtra("mode", "-1");//如果新打开的窗口，什么内容没有写，就返回mode为-1
            } else {
                intent.putExtra("mode", 0);//新建笔记
                intent.putExtra("content", editText.getText().toString());
                intent.putExtra("tag", tag);
                intent.putExtra("time", dateToStr());
            }
        } else if (openMode == 3) {
            //点击原来的笔记，但是没有修改内容
            if (editText.getText().toString().equals(old_content) && !tagChange) {
                intent.putExtra("mode", "-1");
            } else {
                intent.putExtra("content", editText.getText().toString());
                intent.putExtra("mode", 1);//更新笔记
                intent.putExtra("time", dateToStr());
                intent.putExtra("id", id);
                intent.putExtra("tag", tag);
            }
        }
    }

    private String dateToStr() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }
}
