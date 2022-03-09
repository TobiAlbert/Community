package com.tobidaada.community.features.community.presentation.paging.loadstateadapter

import android.view.View
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.tobidaada.community.databinding.ListItemLoadStateFooterBinding

class MemberLoadStateViewHolder(
    itemView: View,
    private val retry: () -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private val binding = ListItemLoadStateFooterBinding.bind(itemView)

    fun bind(loadState: LoadState) {

        if (loadState is LoadState.Error) {
            binding.errorMsg.text = loadState.error.localizedMessage
        }

        binding.errorMsg.isVisible = loadState is LoadState.Error

        binding.progressBar.isVisible = loadState is LoadState.Loading
        binding.retryButton.isVisible = loadState is LoadState.Error
        binding.retryButton.setOnClickListener { retry() }
    }
}