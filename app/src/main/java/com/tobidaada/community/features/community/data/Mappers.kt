package com.tobidaada.community.features.community.data

import com.tobidaada.community.features.community.data.datasource.local.models.UserWithLike
import com.tobidaada.community.features.community.data.datasource.shared.UserDataSource
import com.tobidaada.community.features.community.data.models.UserData
import com.tobidaada.community.features.community.domain.entities.User

fun UserDataSource.toDataObject(): UserData =
    UserData(
        id = id,
        topic = topic,
        firstName = firstName,
        pictureUrl = pictureUrl,
        natives = natives,
        learns = learns,
        referenceCnt = referenceCnt,
    )

fun UserData.toUserDataSource(): UserDataSource =
    UserDataSource(
        id = id,
        topic = topic,
        firstName = firstName,
        pictureUrl = pictureUrl,
        natives = natives,
        learns = learns,
        referenceCnt = referenceCnt,
    )

fun UserWithLike.toDataObject(): UserData =
    UserData(
        id = user.id,
        topic = user.topic,
        firstName = user.firstName,
        pictureUrl = user.pictureUrl,
        natives = user.natives,
        learns = user.learns,
        referenceCnt = user.referenceCnt,
        isLiked = like?.isLiked
    )

fun UserData.toDomainObject(): User =
    User(
        id = id,
        topic = topic,
        firstName = firstName,
        pictureUrl = pictureUrl,
        natives = natives,
        learns = learns,
        referenceCnt = referenceCnt,
        isLiked = isLiked ?: false
    )