package com.tobidaada.community.features.community.data.repository

import androidx.paging.*
import com.tobidaada.community.di.IOCoroutineDispatcher
import com.tobidaada.community.features.community.data.datasource.local.models.UserWithLike
import com.tobidaada.community.features.community.data.datasource.paging.CommunityRemoteMediator
import com.tobidaada.community.features.community.data.toDomainObject
import com.tobidaada.community.features.community.domain.CommunityRepository
import com.tobidaada.community.features.community.domain.entities.User
import com.tobidaada.community.utils.NETWORK_PAGE_SIZE
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ExperimentalPagingApi
class CommunityRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteMediator: CommunityRemoteMediator,
    @IOCoroutineDispatcher private val ioDispatcher: CoroutineDispatcher
) : CommunityRepository {

    override fun getMembers(): Flow<PagingData<User>> {
        val pagingSourceFactory = { localDataSource.getMembers() }

        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            remoteMediator = remoteMediator,
            pagingSourceFactory = pagingSourceFactory
        )
        .flow.map { pagingData: PagingData<UserWithLike> ->
            pagingData.map { it.toDomainObject() }
        }.flowOn(ioDispatcher)
    }

    override suspend fun updateMemberLike(userId: Int, isLiked: Boolean) =
        withContext(ioDispatcher) { localDataSource.updateMemberLike(userId, isLiked) }

}