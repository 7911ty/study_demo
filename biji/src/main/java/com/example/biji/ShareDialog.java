package com.example.biji;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ShareDialog extends Dialog {
    private Context mContext;
    private RecyclerView shareRecyclerView;

    public ShareDialog(@NonNull Context context) {
        super(context);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_dialog);
        initView();
    }

    private void initView() {
        shareRecyclerView = findViewById(R.id.share_rv);
        List<String> list = new ArrayList<>();
        list.add("weixin");
        list.add("weibo");
        list.add("fuzhilianjie");
        list.add("gengduo");
        list.add("pengyouquan");
        ShareDialogAdapter shareDialogAdapter = new ShareDialogAdapter(list, mContext);
        shareDialogAdapter.setOnItemClickListener(new OnRecyclerViewItemClickLister() {
            @Override
            public void onItemClick(View v, int position) {
                String s = list.get(position);
                switch (s) {
                    case "weixin":
                        Toast.makeText(mContext,"weixin",Toast.LENGTH_LONG).show();
                        break;
                    case "weibo":
                        Toast.makeText(mContext,"weibo",Toast.LENGTH_LONG).show();
                        break;
                    case "fuzhilianjie":
                        Toast.makeText(mContext,"复制链接",Toast.LENGTH_LONG).show();
                        break;
                    case "gengduo":
                        Toast.makeText(mContext,"更多",Toast.LENGTH_LONG).show();
                        break;
                    case "pengyouquan":
                        Toast.makeText(mContext,"朋友圈",Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });
        shareRecyclerView.setLayoutManager(new GridLayoutManager(mContext,4));
        shareRecyclerView.setAdapter(shareDialogAdapter);
//        shareRecyclerView.addItemDecoration();
    }

}
