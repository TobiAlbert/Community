package com.tobidaada.community.utils

import com.tobidaada.community.features.community.data.datasource.remote.CommunityResponse
import com.tobidaada.community.features.community.data.datasource.shared.UserDataSource

fun getSuccessResponse(size: Int): CommunityResponse {
    val data = mutableListOf<UserDataSource>()

    repeat(size) { count: Int ->
        data += createUserDataSource(count)
    }

    return CommunityResponse(
        response = data,
        errorCode = null,
        type = "success"
    )
}

fun createUserDataSource(id: Int): UserDataSource =
    UserDataSource(
        id = id,
        topic = "This is a random topic",
        firstName = "First Name",
        pictureUrl = "Picture Url",
        natives = listOf("EN, ES"),
        learns = listOf("DE, PT"),
        referenceCnt = 10
    )