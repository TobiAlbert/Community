package com.tobidaada.community.di

import com.tobidaada.community.features.community.data.datasource.remote.CommunityService
import com.tobidaada.community.features.community.data.datasource.remote.FakeCommunityService
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
   components = [SingletonComponent::class],
   replaces = [RemoteModule::class]
)
abstract class TestRemoteModule {
    @Binds
    @Singleton
    abstract fun provideCommunityService(
        fakeCommunityService: FakeCommunityService
    ): CommunityService
}