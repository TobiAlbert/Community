package com.tobidaada.community.features.community.data.datasource.paging

import android.content.Context
import androidx.paging.*
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.tobidaada.community.features.community.data.datasource.local.AppDatabase
import com.tobidaada.community.features.community.data.datasource.local.models.UserWithLike
import com.tobidaada.community.features.community.data.datasource.remote.CommunityService
import com.tobidaada.community.utils.NETWORK_PAGE_SIZE
import com.tobidaada.community.utils.getSuccessResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@ExperimentalCoroutinesApi
@OptIn(ExperimentalPagingApi::class)
@RunWith(AndroidJUnit4::class)
class CommunityRemoteMediatorTest {

    private lateinit var db: AppDatabase
    private val mockApi: CommunityService = mockk()

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        ).build()

    }

    @After
    fun teardown() {
        db.clearAllTables()
    }

    @Test
    fun refreshLoad_returnsSuccessResult_whenThePageSizeIsReached() = runTest {

        coEvery { mockApi.getMembers(any()) } returns getSuccessResponse(NETWORK_PAGE_SIZE)
        val remoteMediator = CommunityRemoteMediator(mockApi, db)

        val pagingState = PagingState<Int, UserWithLike>(
            listOf(),
            null,
            PagingConfig(NETWORK_PAGE_SIZE),
            0
        )

        val result = remoteMediator.load(LoadType.REFRESH, pagingState)

        assertThat(result is RemoteMediator.MediatorResult.Success).isTrue()
        assertThat((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached).isFalse()
    }

    @Test
    fun refreshLoad_returnsSuccessResult_whenRequestIsSuccessfulButPageSizeAndReached() = runTest {
        coEvery { mockApi.getMembers(any()) } returns getSuccessResponse(1)
        val remoteMediator = CommunityRemoteMediator(mockApi, db)

        val pagingState = PagingState<Int, UserWithLike>(
            listOf(),
            null,
            PagingConfig(NETWORK_PAGE_SIZE),
            0
        )

        val result = remoteMediator.load(LoadType.REFRESH, pagingState)

        assertThat(result is RemoteMediator.MediatorResult.Success).isTrue()
        assertThat((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached).isTrue()
    }

    @Test
    fun refreshLoad_returnsErrorResult_whenRequestThrowsAndException() = runTest {
        coEvery { mockApi.getMembers(any()) } throws IOException("Some not so good things happened")
        val remoteMediator = CommunityRemoteMediator(mockApi, db)

        val pagingState = PagingState<Int, UserWithLike>(
            listOf(),
            null,
            PagingConfig(NETWORK_PAGE_SIZE),
            0
        )

        val result = remoteMediator.load(LoadType.REFRESH, pagingState)

        assertThat(result is RemoteMediator.MediatorResult.Error).isTrue()
    }


}