package com.example.biji;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ShareDialogAdapter extends RecyclerView.Adapter<ShareDialogAdapter.ShareHolder> {
    private List<String> shareInfoList;
    private Context context;
    private OnRecyclerViewItemClickLister myClickItemListener;


    public ShareDialogAdapter(List<String> shareInfoList, Context context) {
        this.shareInfoList = shareInfoList;
        this.context = context;
    }

    @NonNull
    @Override
    public ShareHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ShareHolder(LayoutInflater.from(context).inflate(R.layout.share_dialog_item, parent, false),myClickItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ShareHolder holder, int position) {
        String s = shareInfoList.get(position);
        switch (s) {
            case "weixin":
                holder.shareText.setText("微信");
                break;
            case "weibo":
                holder.shareText.setText("微博");
                break;
            case "fuzhilianjie":
                holder.shareText.setText("复制链接");
                break;
            case "gengduo":
                holder.shareText.setText("更多");
                break;
            case "pengyouquan":
                holder.shareText.setText("朋友圈");
                break;
        }

    }


    /**
     * 定义public方法用以将接口暴露给外部
     * @param listener
     */
    public void setOnItemClickListener(OnRecyclerViewItemClickLister listener) {
        this.myClickItemListener = listener;
    }

    @Override
    public int getItemCount() {
        return shareInfoList.size();
    }

    class ShareHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {

        private final ImageView shareImage;
        private final TextView shareText;
        private final RelativeLayout share_rl;
        private OnRecyclerViewItemClickLister myClickItemListener;
        public ShareHolder(@NonNull View itemView, OnRecyclerViewItemClickLister clickListeer) {
            super(itemView);
            shareImage = itemView.findViewById(R.id.share_image);
            shareText = itemView.findViewById(R.id.share_text);
            share_rl = itemView.findViewById(R.id.share_rl);
            share_rl.setOnClickListener(this);
            itemView.setOnClickListener(this);
            myClickItemListener = clickListeer;
        }

        @Override
        public void onClick(View v) {
            myClickItemListener.onItemClick(v,getAdapterPosition());
        }

        /**
         * 定义public方法用以将接口暴露给外部
         * @param listener
         */
        public void setOnItemClickListener(OnRecyclerViewItemClickLister listener) {
            this.myClickItemListener = listener;
        }
    }
}
