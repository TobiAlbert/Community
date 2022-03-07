package com.tobidaada.community.features.community.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tobidaada.community.features.community.data.datasource.local.models.UserWithLike
import com.tobidaada.community.features.community.data.datasource.shared.UserDataSource
import com.tobidaada.community.features.community.data.models.UserData
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    fun getMembers(): PagingSource<Int, UserWithLike>

    suspend fun addCommunityMembers(members: List<UserDataSource>)

    suspend fun updateMemberLike(userId: Int, isLiked: Boolean)
}