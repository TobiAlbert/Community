package com.tobidaada.community.features.community.data.datasource.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKeys(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "repo_id")
    val repoId: Int,

    @ColumnInfo(name = "previous_key")
    val prevKey: Int? = null,

    @ColumnInfo(name = "next_key")
    val nextKey: Int? = null
)