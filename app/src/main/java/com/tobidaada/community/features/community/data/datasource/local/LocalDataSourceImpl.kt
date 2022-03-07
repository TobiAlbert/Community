package com.tobidaada.community.features.community.data.datasource.local

import androidx.paging.PagingSource
import com.tobidaada.community.features.community.data.datasource.local.dao.CommunityDao
import com.tobidaada.community.features.community.data.datasource.local.models.Like
import com.tobidaada.community.features.community.data.datasource.local.models.UserWithLike
import com.tobidaada.community.features.community.data.datasource.shared.UserDataSource
import com.tobidaada.community.features.community.data.repository.LocalDataSource
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val communityDao: CommunityDao,
): LocalDataSource {

    override fun getMembers(): PagingSource<Int, UserWithLike> = communityDao.getMembers()

    override suspend fun addCommunityMembers(members: List<UserDataSource>) =
        communityDao.addCommunityMembers(members)

    override suspend fun updateMemberLike(userId: Int, isLiked: Boolean) =
        communityDao.updateMemberLike(Like(id = userId, isLiked = isLiked))
}