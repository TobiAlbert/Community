package com.tobidaada.community.features.community.presentation.paging.pagingadapter

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.tobidaada.community.databinding.ListItemMemberBinding
import com.tobidaada.community.features.community.domain.entities.User

class MemberViewHolder(
    private val binding: ListItemMemberBinding,
    private val onItemClicked: (userId: Int, isLiked: Boolean) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User): Unit = with(binding) {
        Log.i("MemberViewHolder", "$user")
        referenceCount.text = user.id.toString()
        Unit
    }
}