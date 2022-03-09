package com.tobidaada.community.features.community.data.datasource.local.models

import androidx.room.*
import com.tobidaada.community.features.community.data.datasource.shared.UserDataSource

@Entity(
    tableName = "likes",
    foreignKeys = [
        ForeignKey(
            entity = UserDataSource::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class Like(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "user_id")
    val id: Int,

    @ColumnInfo(name = "is_liked")
    val isLiked: Boolean = false
)

data class UserWithLike(
    @Embedded
    val user: UserDataSource,

    @Relation(
        parentColumn = "id",
        entityColumn = "user_id"
    )
    val like: Like? = null
)