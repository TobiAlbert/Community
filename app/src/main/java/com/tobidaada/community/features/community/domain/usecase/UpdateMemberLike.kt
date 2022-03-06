package com.tobidaada.community.features.community.domain.usecase

import com.tobidaada.community.di.IOCoroutineDispatcher
import com.tobidaada.community.features.community.domain.CommunityRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UpdateMemberLike @Inject constructor(
    private val communityRepository: CommunityRepository,
    @IOCoroutineDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(params: Params) = withContext(ioDispatcher) {
        communityRepository.updateMemberLike(userId = params.userId, isLiked = params.isLiked)
    }

    data class Params(val userId: Int, val isLiked: Boolean)
}
