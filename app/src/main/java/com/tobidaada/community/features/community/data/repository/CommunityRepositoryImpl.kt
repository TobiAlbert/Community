package com.tobidaada.community.features.community.data.repository

import com.tobidaada.community.di.IOCoroutineDispatcher
import com.tobidaada.community.features.community.ResultWrapper
import com.tobidaada.community.features.community.domain.CommunityRepository
import com.tobidaada.community.features.community.domain.entities.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CommunityRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    @IOCoroutineDispatcher ioCoroutineDispatcher: CoroutineDispatcher
): CommunityRepository {

    override fun getMembers(page: Int): Flow<ResultWrapper<List<User>>> {
        TODO("Not yet implemented")
    }

    override suspend fun updateMemberLike(userId: Int, isLiked: Boolean) {
        TODO("Not yet implemented")
    }
}