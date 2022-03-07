package com.tobidaada.community.features.community.data.repository

import androidx.paging.PagingSource
import com.tobidaada.community.features.community.data.datasource.local.models.UserWithLike
import com.tobidaada.community.features.community.data.datasource.shared.UserDataSource

interface LocalDataSource {

    fun getMembers(): PagingSource<Int, UserWithLike>

    suspend fun addCommunityMembers(members: List<UserDataSource>)

    suspend fun updateMemberLike(userId: Int, isLiked: Boolean)
}