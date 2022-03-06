package com.tobidaada.community.features.community.data.datasource.local

import com.tobidaada.community.features.community.data.datasource.local.models.Like
import com.tobidaada.community.features.community.data.datasource.local.models.UserWithLike
import com.tobidaada.community.features.community.data.datasource.shared.UserDataSource
import com.tobidaada.community.features.community.data.repository.LocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val communityDao: CommunityDao
): LocalDataSource {
    override fun getCommunityMembers(): Flow<List<UserWithLike>> =
        communityDao.getAllCommunityMembers()

    override suspend fun addCommunityMembers(members: List<UserDataSource>) =
        communityDao.addCommunityMembers(members)

    override suspend fun updateMemberLike(userId: Int, isLiked: Boolean) =
        communityDao.updateMemberLike(Like(id = userId, isLiked = isLiked))
}