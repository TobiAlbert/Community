package com.tobidaada.community.features.community.presentation.paging.loadstateadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.tobidaada.community.R

class MemberLoadStateAdapter(
    private val retry: () -> Unit
): LoadStateAdapter<MemberLoadStateViewHolder>() {

    override fun onBindViewHolder(holder: MemberLoadStateViewHolder, loadState: LoadState) =
        holder.bind(loadState)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): MemberLoadStateViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item_load_state_footer, parent, false)
        return MemberLoadStateViewHolder(view, retry)
    }
}