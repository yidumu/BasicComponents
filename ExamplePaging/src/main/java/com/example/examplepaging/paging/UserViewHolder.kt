package com.example.examplepaging.paging

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.examplepaging.R

class UserViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
) {
    private val nameView = itemView.findViewById<TextView>(R.id.text)

    /**
     * Items might be null if they are not paged in yet. PagedListAdapter will re-bind the
     * ViewHolder when Item is loaded.
     */
    fun bind(item: User?) {
        Log.d("TAG", "bind() called with: item = $item")
        if (item != null) nameView.text = item.id.toString()
    }
}