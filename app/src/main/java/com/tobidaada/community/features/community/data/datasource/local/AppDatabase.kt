package com.tobidaada.community.features.community.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tobidaada.community.features.community.data.datasource.local.models.Like
import com.tobidaada.community.features.community.data.datasource.shared.UserDataSource

@Database(entities = [UserDataSource::class, Like::class], version = 1, exportSchema = false)
@TypeConverters(StringConverters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun communityDao(): CommunityDao
}