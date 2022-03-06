package com.tobidaada.community.features.community.data.datasource.local

import com.tobidaada.community.features.community.data.datasource.local.models.Like
import com.tobidaada.community.features.community.data.datasource.shared.UserDataSource
import com.tobidaada.community.features.community.data.models.UserData
import com.tobidaada.community.features.community.data.repository.LocalDataSource
import com.tobidaada.community.features.community.data.toDataObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val communityDao: CommunityDao
): LocalDataSource {
    override fun getCommunityMembers(): Flow<List<UserData>> =
        communityDao.getAllCommunityMembers().map { usersWithLike ->
            usersWithLike.map { it.toDataObject() }
        }

    override suspend fun addCommunityMembers(members: List<UserDataSource>) =
        communityDao.addCommunityMembers(members)

    override suspend fun updateMemberLike(userId: Int, isLiked: Boolean) =
        communityDao.updateMemberLike(Like(id = userId, isLiked = isLiked))
}