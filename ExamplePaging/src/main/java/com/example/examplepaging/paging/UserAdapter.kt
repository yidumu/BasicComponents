package com.example.examplepaging.paging

import android.util.Log
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil

class UserAdapter(diffCallback: DiffUtil.ItemCallback<User>) :
    PagingDataAdapter<User, UserViewHolder>(diffCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserViewHolder {
        Log.d("TAG", "onCreateViewHolder() called with: parent = $parent, viewType = $viewType")
        return UserViewHolder(parent)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        Log.d("TAG", "onBindViewHolder() called with: holder = $holder, position = $position")
        val item = getItem(position)
        // Note that item may be null. ViewHolder must support binding a
        // null item as a placeholder.
        holder.bind(item)
    }
}