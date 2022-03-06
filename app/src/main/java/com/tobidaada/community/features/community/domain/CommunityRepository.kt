package com.tobidaada.community.features.community.domain

import com.tobidaada.community.features.community.ResultWrapper
import com.tobidaada.community.features.community.domain.entities.User
import kotlinx.coroutines.flow.Flow

interface CommunityRepository {
    fun getMembers(page: Int): Flow<ResultWrapper<List<User>>>

    suspend fun updateMemberLike(userId: Int, isLiked: Boolean)
}