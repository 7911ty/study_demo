package com.example.studydemo.viewpage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studydemo.R;

import java.util.ArrayList;
import java.util.List;

public class ViewpageAdapter extends RecyclerView.Adapter<ViewpageAdapter.ViewpageViewHolder> {
    private List<String> list = new ArrayList<>();

    public ViewpageAdapter() {
        list.add("1");
        list.add("12");
        list.add("13");
        list.add("14");
        list.add("15");
        list.add("16");
        list.add("17");
    }

    @NonNull
    @Override
    public ViewpageAdapter.ViewpageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewpageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_viewpage,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewpageAdapter.ViewpageViewHolder holder, int position) {
        holder.textView.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewpageViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public ViewpageViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_text);
        }
    }
}
