package com.tobidaada.community.features.community.presentation.paging.pagingadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.tobidaada.community.databinding.ListItemMemberBinding
import com.tobidaada.community.features.community.domain.entities.User

class MemberAdapter(
    private val onItemClicked: (userId: Int, isLiked: Boolean) -> Unit
) : PagingDataAdapter<User, MemberViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemMemberBinding.inflate(inflater, parent, false)
        return MemberViewHolder(binding, onItemClicked)
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int): Unit =
        getItem(position)?.let { holder.bind(it) } ?: Unit

    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
                oldItem == newItem
        }
    }
}