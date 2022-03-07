package com.tobidaada.community.features.community.data

import com.tobidaada.community.features.community.data.datasource.local.models.UserWithLike
import com.tobidaada.community.features.community.domain.entities.User

fun UserWithLike.toDomainObject(): User =
    User(
        id = user.id,
        topic = user.topic,
        firstName = user.firstName,
        pictureUrl = user.pictureUrl,
        natives = user.natives,
        learns = user.learns,
        referenceCnt = user.referenceCnt,
        isLiked = like?.isLiked ?: false
    )