package com.tobidaada.community.features.community.data.datasource.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface CommunityService {

    @GET("community_{page}.json")
    suspend fun getMembers(@Path("page") page: Int): CommunityResponse
}