package com.tobidaada.community.features.community.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.tobidaada.community.di.IOCoroutineDispatcher
import com.tobidaada.community.features.community.domain.entities.User
import com.tobidaada.community.features.community.domain.usecase.GetMembers
import com.tobidaada.community.features.community.domain.usecase.UpdateMemberLike
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MemberViewModel @Inject constructor(
    private val getMembers: GetMembers,
    private val updateMemberLike: UpdateMemberLike,
    @IOCoroutineDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {

    var pagingFlow: Flow<PagingData<User>> = getMembers.invoke().cachedIn(viewModelScope)

    fun updateMemberLike(userId: Int, isLiked: Boolean) {
        viewModelScope.launch(ioDispatcher) {
            updateMemberLike.invoke(UpdateMemberLike.Params(userId, isLiked))
        }
    }
}
