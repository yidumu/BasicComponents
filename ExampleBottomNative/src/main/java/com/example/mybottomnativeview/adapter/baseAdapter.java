package com.example.mybottomnativeview.adapter;


import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mybottomnativeview.R;

import java.util.ArrayList;
import java.util.List;

public class baseAdapter extends RecyclerView.Adapter<baseAdapter.ViewHolder> {
    private static final String TAG = baseAdapter.class.getSimpleName();
    private int selectedIndex = 0; //记录当前位置
    private Context mContext;

    private void setSelectedIndex(int position){
        this.selectedIndex = position;
        notifyDataSetChanged();
    }
    private ArrayList<String> mList;
    public interface OnitemClick {
        void onItemClick(int position);
    }
    public interface OnLongClick {
        void onLongClick(int position);
    }
    private OnitemClick onitemClick;
    private OnLongClick onLongClick;
    public void setOnitemClickLintener (OnitemClick onitemClick) {
        this.onitemClick = onitemClick;
    }
    public void setOnLongClickListener (OnLongClick onLongClick) {
        this.onLongClick = onLongClick;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public RelativeLayout relativeLayout;
        public ViewHolder(View v) {
            super(v);
            textView = v.findViewById(R.id.tv_item);
            relativeLayout = v.findViewById(R.id.rl_item);
        }
    }

    public baseAdapter(ArrayList<String> list, Context context) {
        mList = list;
        mContext=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_display, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Log.d(TAG,"onBindViewHolder position : "+ position);
        //加载第一个界面
        if(position == selectedIndex){
            //holder.relativeLayout.setBackgroundResource(R.color.colorAccent);
            holder.relativeLayout.setSelected(true);
        }else {
            holder.relativeLayout.setSelected(false);
            //holder.relativeLayout.setBackgroundResource(R.color.colorPrimary);
        }
        holder.textView.setText(mList.get(position));
        if(onitemClick!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onitemClick.onItemClick(position);
                    setSelectedIndex(position);
                }
            });
        }
        if(onLongClick!=null){
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onLongClick.onLongClick(position);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void notifyData(List<String> list){
        mList.clear();
        mList.addAll(list);
        selectedIndex = 0;
        notifyDataSetChanged();
        onRefresh.refresh(0);
    }

    public interface onRefreshFragment {
         void refresh(int item);
    }
    private onRefreshFragment onRefresh;
    public void setonRefreshFragment(onRefreshFragment onRefresh){
        this.onRefresh = onRefresh;
    }

}
