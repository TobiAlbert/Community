package com.tobidaada.community.features.community.domain

import androidx.paging.PagingData
import com.tobidaada.community.features.community.domain.entities.User
import kotlinx.coroutines.flow.Flow

interface CommunityRepository {

    fun getMembers(): Flow<PagingData<User>>

    suspend fun updateMemberLike(userId: Int, isLiked: Boolean)
}