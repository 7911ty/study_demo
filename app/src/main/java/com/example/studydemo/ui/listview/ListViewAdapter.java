package com.example.studydemo.ui.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.studydemo.R;

public class ListViewAdapter extends BaseAdapter {
    private Context mcontext;
    private LayoutInflater layoutInflater;

    public ListViewAdapter(Context mcontext) {
        this.mcontext = mcontext;
        this.layoutInflater = LayoutInflater.from(mcontext);
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolder {
        public ImageView imageView;
        public TextView tv_title;
        public TextView tv_time;
        public TextView tv_content;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.layout_listview_item, null);
            holder = new ViewHolder();
            holder.imageView = convertView.findViewById(R.id.listview_iv);
            holder.tv_title = convertView.findViewById(R.id.listview_title);
            holder.tv_time = convertView.findViewById(R.id.listview_time);
            holder.tv_content = convertView.findViewById(R.id.listview_content);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_title.setText("111");
        holder.tv_time.setText("1112");
        holder.tv_content.setText("1113");
        //Glide.with(mcontext).load();
        return convertView;
    }
}
