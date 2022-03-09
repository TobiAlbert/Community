package com.tobidaada.community.features.community.domain.usecase

import androidx.paging.PagingData
import com.tobidaada.community.di.IOCoroutineDispatcher
import com.tobidaada.community.features.community.domain.CommunityRepository
import com.tobidaada.community.features.community.domain.entities.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetMembers @Inject constructor(
    private val communityRepository: CommunityRepository,
    @IOCoroutineDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    operator fun invoke(): Flow<PagingData<User>> =
        communityRepository.getMembers().flowOn(ioDispatcher)
}

