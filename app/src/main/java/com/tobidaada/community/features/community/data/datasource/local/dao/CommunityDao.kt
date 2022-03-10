package com.tobidaada.community.features.community.data.datasource.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.tobidaada.community.features.community.data.datasource.local.models.Like
import com.tobidaada.community.features.community.data.datasource.local.models.UserWithLike
import com.tobidaada.community.features.community.data.datasource.shared.UserDataSource

@Dao
interface CommunityDao {

    @Transaction
    @Query("SELECT * FROM users ORDER BY id ASC")
    fun getMembers(): PagingSource<Int, UserWithLike>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCommunityMembers(members: List<UserDataSource>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateMemberLike(like: Like)

    @Query("DELETE FROM users")
    suspend fun clearAll()
}