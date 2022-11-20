package com.example.studydemo.ui.recyclerview.delegate;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.studydemo.R;
import com.example.studydemo.base.baseadapter.base.ItemViewDelegate;
import com.example.studydemo.base.baseadapter.base.ViewHolder;
import com.example.studydemo.ui.recyclerview.beans.BaseAdapterBean;
import com.example.studydemo.ui.recyclerview.beans.DelegateNo2Bean;

public class BaseAdapterNo2Delegate implements ItemViewDelegate<BaseAdapterBean> {
    private Context context;

    public BaseAdapterNo2Delegate(Context context) {
        this.context = context;
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.delegate_no2;
    }

    @Override
    public boolean isForViewType(BaseAdapterBean item, int position) {
        if (item == null) {
            return false;
        }
        String type = item.getType();
        return "2".equals(type);
    }

    @Override
    public void convert(ViewHolder holder, BaseAdapterBean baseAdapterBean, int position) {
        DelegateNo2Bean delegateNo2Bean = baseAdapterBean.getDelegateNo2Bean();
        ImageView viewById = holder.itemView.findViewById(R.id.no2_iv);
        Glide.with(context).load(delegateNo2Bean.getImageUrl()).into(viewById);
    }
}
