package com.tobidaada.community.features.community.data.datasource.remote

import com.google.gson.annotations.SerializedName
import com.tobidaada.community.features.community.data.datasource.shared.UserDataSource

data class CommunityResponse(
    @SerializedName("response")
    val response: List<UserDataSource>,

    @SerializedName("errorCode")
    val errorCode: String? = null,

    @SerializedName("type")
    val type: String
)