package com.tobidaada.community.features.community.data

import com.tobidaada.community.features.community.data.datasource.shared.UserDataSource
import com.tobidaada.community.features.community.data.models.UserData

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