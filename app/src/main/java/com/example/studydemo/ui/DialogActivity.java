package com.example.studydemo.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.base.ui.BaseActivity;
import com.example.base.utils.StatusBarUtil;
import com.example.studydemo.R;

import java.util.Arrays;

public class DialogActivity extends BaseActivity {

    private View sec_bt_1;
    private View sec_bt_2;
    private View sec_bt_3;
    private View sec_bt_4;

    @Override
    protected int initLayout() {
        return R.layout.activity_dialog;
    }

    @Override
    protected void initView() {
        StatusBarUtil.setTranslucentStatus(this);
        StatusBarUtil.setAndroidNativeLightStatusBar(this, true);
        sec_bt_1 = findViewById(R.id.sec_bt_1);
        sec_bt_2 = findViewById(R.id.sec_bt_2);
        sec_bt_3 = findViewById(R.id.sec_bt_3);
        sec_bt_4 = findViewById(R.id.sec_bt_4);
    }

    @Override
    protected void initData() {
        sec_bt_1.setOnClickListener(view ->
                new AlertDialog.Builder(DialogActivity.this)
                        .setTitle("This is title")
                        .setMessage("This is message")
                        .setPositiveButton("点击同意", (dialog, which) -> {
                            Toast.makeText(DialogActivity.this, "点击了同意", Toast.LENGTH_LONG).show();
                        })
                        .setNegativeButton("点击取消", (dialog, which) -> {
                            Toast.makeText(DialogActivity.this, "点击了取消", Toast.LENGTH_LONG).show();
                        })
                        .setIcon(R.drawable.ic_app)
                        .show()
        );
        sec_bt_2.setOnClickListener(v -> {
            String[] strings = new String[]{"item1", "item2", "item3"};
            new AlertDialog.Builder(DialogActivity.this)
                    .setTitle("This is title2")
                    // setItems时不能setMessage
                    // .setMessage("This is message2")
                    .setPositiveButton("点击同意", (dialog, which) -> {
                        Toast.makeText(DialogActivity.this, "点击了同意", Toast.LENGTH_LONG).show();
                    })
                    .setNegativeButton("点击取消", (dialog, which) -> {
                        Toast.makeText(DialogActivity.this, "点击了取消", Toast.LENGTH_LONG).show();
                    })
                    .setItems(strings, (dialog, which) -> {
                        Toast.makeText(DialogActivity.this, "点击了第" + strings[which] + "个。", Toast.LENGTH_LONG).show();
                    })
                    .setIcon(R.drawable.ic_app)
                    .show();
        });
        sec_bt_3.setOnClickListener(v -> {
            String[] strings = new String[]{"item1", "item2", "item3", "item4", "item5", "item6"};
            boolean[] booleans = new boolean[strings.length];
            new AlertDialog.Builder(DialogActivity.this)
                    .setTitle("This is title2")
                    // setItems时不能setMessage
                    // .setMessage("This is message2")
                    .setPositiveButton("点击同意", (dialog, which) -> {
                        Log.d(TAG,"booleans: " + Arrays.toString(booleans));
                        Toast.makeText(DialogActivity.this, "点击了同意", Toast.LENGTH_LONG).show();
                    })
                    .setNegativeButton("点击取消",(dialog, which) -> {
                        Toast.makeText(DialogActivity.this, "点击了取消", Toast.LENGTH_LONG).show();
                    } )
                    .setMultiChoiceItems(strings, booleans, (dialog, which, isChecked) -> Toast.makeText(DialogActivity.this, "点击了第" + strings[which] + "个。", Toast.LENGTH_LONG).show())
                    .setIcon(R.drawable.ic_app)
                    .show();
        });
        sec_bt_4.setOnClickListener(v -> {
            String[] strings = new String[]{"item1", "item2", "item3", "item4", "item5", "item6"};
            boolean[] booleans = new boolean[strings.length];
            new AlertDialog.Builder(DialogActivity.this)
                    .setTitle("This is title2")
                    // setItems时不能setMessage
                    // .setMessage("This is message2")
                    .setPositiveButton("点击同意", (dialog, which) -> {
                        Log.d(TAG,"booleans: " + Arrays.toString(booleans));
                        Toast.makeText(DialogActivity.this, "点击了同意", Toast.LENGTH_LONG).show();
                    })
                    .setNegativeButton("点击取消",(dialog, which) -> {
                        Toast.makeText(DialogActivity.this, "点击了取消", Toast.LENGTH_LONG).show();
                    } )
                    .setSingleChoiceItems(strings, -1, (dialog, which) -> Toast.makeText(DialogActivity.this, "点击了第" + strings[which] + "个。", Toast.LENGTH_LONG).show())
                    .setIcon(R.drawable.ic_app)
                    .show();
        });
    }
}