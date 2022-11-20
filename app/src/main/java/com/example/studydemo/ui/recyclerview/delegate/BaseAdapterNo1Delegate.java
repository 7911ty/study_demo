package com.example.studydemo.ui.recyclerview.delegate;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.studydemo.R;
import com.example.studydemo.base.baseadapter.base.ItemViewDelegate;
import com.example.studydemo.base.baseadapter.base.ViewHolder;
import com.example.studydemo.ui.recyclerview.beans.BaseAdapterBean;
import com.example.studydemo.ui.recyclerview.beans.DelegateNo1Bean;

public class BaseAdapterNo1Delegate implements ItemViewDelegate<BaseAdapterBean> {
    private Context context;
    public BaseAdapterNo1Delegate(Context context) {
        this.context = context;
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.delegate_no1;
    }

    @Override
    public boolean isForViewType(BaseAdapterBean item, int position) {
        if (item == null) {
            return false;
        }
        String type = item.getType();
        return  "1".equals(type);
    }

    @Override
    public void convert(ViewHolder holder, BaseAdapterBean baseAdapterBean, int position) {
        DelegateNo1Bean delegateNo1Bean = baseAdapterBean.getDelegateNo1Bean();
        ImageView imageView = holder.itemView.findViewById(R.id.no1_iv);
        TextView no1_title_tv = holder.itemView.findViewById(R.id.no1_title_tv);
        TextView no1_author_tv = holder.itemView.findViewById(R.id.no1_author_tv);
        no1_title_tv.setText(delegateNo1Bean.getTextTitle());
        no1_author_tv.setText(delegateNo1Bean.getTextAutor());
        Glide.with(context).load(delegateNo1Bean.getImageUrl()).into(imageView);
    }
}
