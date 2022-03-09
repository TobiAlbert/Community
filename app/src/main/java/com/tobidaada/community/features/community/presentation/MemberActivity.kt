package com.tobidaada.community.features.community.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.tobidaada.community.databinding.ActivityMemberBinding
import com.tobidaada.community.features.community.presentation.paging.loadstateadapter.MemberLoadStateAdapter
import com.tobidaada.community.features.community.presentation.paging.pagingadapter.MemberAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MemberActivity : AppCompatActivity() {

    private val memberViewModel: MemberViewModel by viewModels()
    private lateinit var binding: ActivityMemberBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMemberBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // setup adapter
        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)

        val memberAdapter = MemberAdapter(::onItemClicked)
        binding.memberRv.apply {
            addItemDecoration(itemDecoration)
            adapter =
                memberAdapter.withLoadStateFooter(
                    footer = MemberLoadStateAdapter { memberAdapter.retry() }
                )
        }

        lifecycleScope.launch {
            memberViewModel.getMembers().distinctUntilChanged()
                .collectLatest { memberAdapter.submitData(it) }
        }
    }

    private fun onItemClicked(userId: Int, isLiked: Boolean) {
        memberViewModel.updateMemberLike(userId, isLiked)
    }
}