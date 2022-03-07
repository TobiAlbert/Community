package com.tobidaada.community.di

import android.content.Context
import androidx.room.Room
import com.tobidaada.community.features.community.data.datasource.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun providesAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app"
        ).build()

    @Provides
    @Singleton
    fun provideCommunityDao(db: AppDatabase) = db.communityDao()

    @Provides
    @Singleton
    fun provideRemoteKeysDao(db: AppDatabase) = db.remoteKeysDao()
}