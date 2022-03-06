package com.tobidaada.community.features.community.data.datasource.shared

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "users")
data class UserDataSource(

    @SerializedName("id")
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = false)
    val id: Int,

    @SerializedName("topic")
    @ColumnInfo(name = "topic")
    val topic: String,

    @SerializedName("firstName")
    @ColumnInfo(name = "first_name")
    val firstName: String,

    @SerializedName("pictureUrl")
    @ColumnInfo(name = "picture_url")
    val pictureUrl: String,

    @SerializedName("natives")
    @ColumnInfo(name = "natives")
    val natives: List<String>,

    @SerializedName("learns")
    @ColumnInfo(name = "learns")
    val learns: List<String>,

    @SerializedName("referenceCnt")
    @ColumnInfo(name = "reference_count")
    val referenceCnt: Int,
)