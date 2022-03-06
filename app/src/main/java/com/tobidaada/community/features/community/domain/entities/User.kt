package com.tobidaada.community.features.community.domain.entities

data class User(
    val id: Int,
    val topic: String,
    val firstName: String,
    val pictureUrl: String,
    val natives: List<String>,
    val learns: List<String>,
    val referenceCnt: Int,
    val isLiked: Boolean
)