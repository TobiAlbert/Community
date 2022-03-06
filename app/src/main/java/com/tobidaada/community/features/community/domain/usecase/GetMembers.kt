package com.tobidaada.community.features.community.domain.usecase

import com.tobidaada.community.di.IOCoroutineDispatcher
import com.tobidaada.community.features.community.ResultWrapper
import com.tobidaada.community.features.community.domain.CommunityRepository
import com.tobidaada.community.features.community.domain.entities.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetMembers @Inject constructor(
    private val communityRepository: CommunityRepository,
    @IOCoroutineDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(page: Int): Flow<ResultWrapper<List<User>>> =
        withContext(ioDispatcher) { communityRepository.getMembers(page) }
}

