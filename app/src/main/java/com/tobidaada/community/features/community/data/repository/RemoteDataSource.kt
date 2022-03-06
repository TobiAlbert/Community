package com.tobidaada.community.features.community.data.repository

import com.tobidaada.community.features.community.ResultWrapper
import com.tobidaada.community.features.community.data.models.UserData

interface RemoteDataSource {
    suspend fun getCommunityMembers(page: Int): ResultWrapper<List<UserData>>
}