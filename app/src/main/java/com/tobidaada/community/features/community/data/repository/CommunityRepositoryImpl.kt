package com.tobidaada.community.features.community.data.repository

import com.tobidaada.community.di.IOCoroutineDispatcher
import com.tobidaada.community.features.community.ResultWrapper
import com.tobidaada.community.features.community.data.models.UserData
import com.tobidaada.community.features.community.data.toDomainObject
import com.tobidaada.community.features.community.data.toUserDataSource
import com.tobidaada.community.features.community.domain.CommunityRepository
import com.tobidaada.community.features.community.domain.entities.User
import com.tobidaada.community.utils.networkBoundResource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CommunityRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    @IOCoroutineDispatcher private val ioDispatcher: CoroutineDispatcher
) : CommunityRepository {

    override fun getMembers(page: Int): Flow<ResultWrapper<List<User>>> =
        networkBoundResource(
            query = { localDataSource.getCommunityMembers() },
            fetch = { remoteDataSource.getCommunityMembers(1) },
            isFetchSuccessful = ::isFetchSuccessful,
            getErrorMessage = ::getErrorMessage,
            saveFetchResult = ::saveFetchResult
        )
        .map(::mapToDomainModel)
        .flowOn(ioDispatcher)

    private fun mapToDomainModel(response: ResultWrapper<List<UserData>>): ResultWrapper<List<User>> =
        when (response) {
            is ResultWrapper.Error -> response.copy()
            is ResultWrapper.Success -> ResultWrapper.Success(response.value.map { it.toDomainObject() })
        }

    private fun isFetchSuccessful(response: ResultWrapper<List<UserData>>): Boolean =
        response is ResultWrapper.Success

    override suspend fun updateMemberLike(userId: Int, isLiked: Boolean) =
        withContext(ioDispatcher) { localDataSource.updateMemberLike(userId, isLiked) }

    private suspend fun saveFetchResult(result: ResultWrapper<List<UserData>>): Unit =
        when (result) {
            is ResultWrapper.Success -> localDataSource.addCommunityMembers(result.value.map { it.toUserDataSource() })
            else -> Unit
        }

    private fun getErrorMessage(response: ResultWrapper<List<UserData>>): String =
        when (response) {
            is ResultWrapper.Error -> response.message
            else -> ""
        }
}