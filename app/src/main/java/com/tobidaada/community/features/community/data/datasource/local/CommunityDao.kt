package com.tobidaada.community.features.community.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tobidaada.community.features.community.data.datasource.local.models.Like
import com.tobidaada.community.features.community.data.datasource.local.models.UserWithLike
import com.tobidaada.community.features.community.data.datasource.shared.UserDataSource
import kotlinx.coroutines.flow.Flow

@Dao
interface CommunityDao {

    @Query("SELECT * FROM users")
    fun getAllCommunityMembers(): Flow<List<UserWithLike>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCommunityMembers(members: List<UserDataSource>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateMemberLike(like: Like)
}