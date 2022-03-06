package com.tobidaada.community.di

import com.tobidaada.community.features.community.data.datasource.remote.RemoteDataSourceImpl
import com.tobidaada.community.features.community.data.repository.CommunityRepositoryImpl
import com.tobidaada.community.features.community.data.repository.RemoteDataSource
import com.tobidaada.community.features.community.domain.CommunityRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CommunityFeature {
    @Binds
    abstract fun bindsRemoteDataSource(
        remoteDataSourceImpl: RemoteDataSourceImpl
    ): RemoteDataSource
}

@Module
@InstallIn(SingletonComponent::class)
abstract class CommunityRepositoryModule {
    @Binds
    abstract fun bindsCommunityRepository(
        communityRepositoryImpl: CommunityRepositoryImpl
    ): CommunityRepository
}
