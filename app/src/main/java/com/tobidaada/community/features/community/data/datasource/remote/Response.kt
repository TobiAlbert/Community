package com.tobidaada.community.features.community.data.datasource.remote

import com.google.gson.annotations.SerializedName

data class Community(
    @SerializedName("response")
    val response: List<UserRemote>,

    @SerializedName("errorCode")
    val errorCode: String? = null,

    @SerializedName("type")
    val type: String
)

data class UserRemote(
    @SerializedName("id")
    val id: Int,

    @SerializedName("topic")
    val topic: String,

    @SerializedName("firstName")
    val firstName: String,

    @SerializedName("pictureUrl")
    val pictureUrl: String,

    @SerializedName("natives")
    val natives: List<String>,

    @SerializedName("learns")
    val learns: List<String>,

    @SerializedName("referenceCnt")
    val referenceCnt: Int,
)