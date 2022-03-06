package com.tobidaada.community.features.community.data.models

data class UserData(
    val id: Int,
    val topic: String,
    val firstName: String,
    val pictureUrl: String,
    val natives: List<String>,
    val learns: List<String>,
    val referenceCnt: Int,
    val isLiked: Boolean? = null
)