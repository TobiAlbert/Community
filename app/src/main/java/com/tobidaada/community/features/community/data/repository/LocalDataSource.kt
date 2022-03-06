package com.tobidaada.community.features.community.data.repository

import com.tobidaada.community.features.community.data.datasource.local.models.UserWithLike
import com.tobidaada.community.features.community.data.datasource.shared.UserDataSource
import com.tobidaada.community.features.community.data.models.UserData
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    fun getCommunityMembers(): Flow<List<UserData>>

    suspend fun addCommunityMembers(members: List<UserDataSource>)

    suspend fun updateMemberLike(userId: Int, isLiked: Boolean)
}