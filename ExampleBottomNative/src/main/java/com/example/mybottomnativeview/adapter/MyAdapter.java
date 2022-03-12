package com.example.mybottomnativeview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mybottomnativeview.R;

import java.util.List;

public class MyAdapter<T> extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<T> list;

    public interface OnitemClick {
        void onItemClick(int position);
    }

    public interface OnLongClick {
        void onLongClick(int position);
    }

    private OnitemClick onitemClick;
    private OnLongClick onLongClick;

    public void setOnitemClickLintener(OnitemClick onitemClick) {
        this.onitemClick = onitemClick;
    }

    public void setOnLongClickListener(OnLongClick onLongClick) {
        this.onLongClick = onLongClick;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;

        public MyViewHolder(View v) {
            super(v);
            textView = v.findViewById(R.id.tv_item);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<T> list) {
        this.list = list;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_display, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }




    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textView.setText(list.get(position).toString());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onitemClick != null) {
                    onitemClick.onItemClick(position);
                }

            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onitemClick != null) {
                    onLongClick.onLongClick(position);
                }

            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public void addData(int positon, T e) {
        list.add(e);
        notifyItemInserted(positon);

     /*   notifyItemChanged();
        notifyDataSetChanged();
        notifyItemRangeChanged();
        notifyItemRangeRemoved();*/
    }

    public void removedData(int positon){
        list.remove(positon);
        notifyItemRemoved(positon);
    }

    public void notifyData(int positon,int itemCount){
        notifyItemRangeChanged(positon,itemCount);
    }

}
