package com.tobidaada.community.features.community.data.datasource.remote

import com.tobidaada.community.utils.NETWORK_PAGE_SIZE
import com.tobidaada.community.utils.TestMode
import com.tobidaada.community.utils.getSuccessResponse
import javax.inject.Inject

class FakeCommunityService @Inject constructor(): CommunityService {

    var testMode: TestMode = TestMode.SuccessMode
    var pageCount: Int = NETWORK_PAGE_SIZE

    override suspend fun getMembers(page: Int): CommunityResponse {
        return getSuccessResponse(pageCount)
    }
}