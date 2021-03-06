package com.tobidaada.community.di

import androidx.paging.ExperimentalPagingApi
import com.tobidaada.community.features.community.data.datasource.local.LocalDataSourceImpl
import com.tobidaada.community.features.community.data.repository.CommunityRepositoryImpl
import com.tobidaada.community.features.community.data.repository.LocalDataSource
import com.tobidaada.community.features.community.domain.CommunityRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CommunityFeature {
    @Binds
    abstract fun bindsLocalDataSource(
        localDataSourceImpl: LocalDataSourceImpl
    ): LocalDataSource
}

@Module
@InstallIn(SingletonComponent::class)
abstract class CommunityRepositoryModule {
    @OptIn(ExperimentalPagingApi::class)
    @Binds
    abstract fun bindsCommunityRepository(
        communityRepositoryImpl: CommunityRepositoryImpl
    ): CommunityRepository
}
