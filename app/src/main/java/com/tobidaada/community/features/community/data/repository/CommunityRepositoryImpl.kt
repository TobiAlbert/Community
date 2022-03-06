package com.tobidaada.community.features.community.data.repository

import com.tobidaada.community.features.community.ResultWrapper
import com.tobidaada.community.features.community.domain.CommunityRepository
import com.tobidaada.community.features.community.domain.entities.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CommunityRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): CommunityRepository {

    override fun getCommunityMembers(page: Int): Flow<ResultWrapper<List<User>>> {
        TODO("Not yet implemented")
    }
}