package com.tobidaada.community.features.community.data.datasource.remote

import com.tobidaada.community.di.IOCoroutineDispatcher
import com.tobidaada.community.features.community.ResultWrapper
import com.tobidaada.community.features.community.data.models.UserData
import com.tobidaada.community.features.community.data.repository.RemoteDataSource
import com.tobidaada.community.features.community.data.toDataObject
import com.tobidaada.community.features.community.transformResponse
import com.tobidaada.community.utils.safeApiCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val communityService: CommunityService,
    @IOCoroutineDispatcher private val ioDispatcher: CoroutineDispatcher
): RemoteDataSource {

    override suspend fun getCommunityMembers(page: Int): ResultWrapper<List<UserData>> =
        withContext(ioDispatcher) {
            val response: ResultWrapper<Community> = safeApiCall(ioDispatcher) { communityService.getCommunity(page) }

            return@withContext transformResponse(response) { result: ResultWrapper.Success<Community> ->
                result.value.response.map { it.toDataObject() }
            }
        }
}