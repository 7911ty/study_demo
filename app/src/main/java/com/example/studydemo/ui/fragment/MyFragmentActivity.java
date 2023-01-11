package com.example.studydemo.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.base.ui.BaseActivity;
import com.example.studydemo.R;
import com.example.studydemo.ui.fragment.bean.Student;

public class MyFragmentActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "MyFragmentActivity";

    @Override
    protected int initLayout() {
        return R.layout.activity_fragment;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: savedInstanceState = " + savedInstanceState);
    }

    @Override
    protected void initView() {
        Button button1 = findViewById(R.id.fragment_btn_1);
        Button button2 = findViewById(R.id.fragment_btn_2);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    @Override
    protected void initData() {
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fragment_btn_1) {
            Bundle bundle = new Bundle();
            bundle.putString("param1", "i love zxj");
            Student zs = new Student(1, "zs");
            bundle.putParcelable("student", zs);
            BlankFragment blankFragment = new BlankFragment();
            blankFragment.setArguments(bundle);
            blankFragment.setCollBack(new IFragmentCollBack() {
                @Override
                public void sendMsgToActivity(String msg) {
                    Toast.makeText(MyFragmentActivity.this, msg, Toast.LENGTH_LONG).show();
                }

                @Override
                public String getMsgFromActivity() {
                    return null;
                }
            });
            replaceFragment(blankFragment);
        } else if (v.getId() == R.id.fragment_btn_2) {
            replaceFragment(new ItemFragment());
        }
    }

    private void replaceFragment(Fragment fragment1) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_1, fragment1);
        //fragmentTransaction
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
