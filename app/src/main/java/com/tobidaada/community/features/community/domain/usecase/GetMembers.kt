package com.tobidaada.community.features.community.domain.usecase

import com.tobidaada.community.features.community.domain.CommunityRepository
import javax.inject.Inject

class GetMembers @Inject constructor(
    private val communityRepository: CommunityRepository
) {

}

