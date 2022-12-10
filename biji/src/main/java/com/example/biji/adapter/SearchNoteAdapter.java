package com.example.biji.adapter;

import android.content.Context;

import com.example.base.ui.MultiItemTypeAdapter;
import com.example.base.ui.base.ItemViewDelegate;
import com.example.base.ui.base.ViewHolder;

import java.util.List;

public class SearchNoteAdapter extends MultiItemTypeAdapter {
    public SearchNoteAdapter(Context context, List datas) {
        super(context, datas);
        addItemViewDelegate(new ItemViewDelegate() {
            @Override
            public int getItemViewLayoutId() {
                return 0;
            }

            @Override
            public boolean isForViewType(Object item, int position) {
                return false;
            }

            @Override
            public void convert(ViewHolder holder, Object o, int position) {

            }
        });
    }

    class SearchNotesDelegate implements ItemViewDelegate{
        @Override
        public int getItemViewLayoutId() {
            return 0;
        }

        @Override
        public boolean isForViewType(Object item, int position) {
            return false;
        }

        @Override
        public void convert(ViewHolder holder, Object o, int position) {

        }
    }
}
