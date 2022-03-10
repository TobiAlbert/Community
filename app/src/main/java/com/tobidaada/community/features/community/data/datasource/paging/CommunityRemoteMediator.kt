package com.tobidaada.community.features.community.data.datasource.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.tobidaada.community.features.community.data.datasource.local.AppDatabase
import com.tobidaada.community.features.community.data.datasource.local.models.RemoteKeys
import com.tobidaada.community.features.community.data.datasource.local.models.UserWithLike
import com.tobidaada.community.features.community.data.datasource.remote.CommunityService
import com.tobidaada.community.features.community.data.datasource.shared.UserDataSource
import com.tobidaada.community.utils.COMMUNITY_STARTING_PAGE_INDEX
import com.tobidaada.community.utils.NETWORK_PAGE_SIZE
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class CommunityRemoteMediator @Inject constructor(
    private val communityService: CommunityService,
    private val appDatabase: AppDatabase
): RemoteMediator<Int, UserWithLike>() {

    override suspend fun initialize(): InitializeAction = InitializeAction.LAUNCH_INITIAL_REFRESH

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UserWithLike>
    ): MediatorResult {
        val page: Int = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: COMMUNITY_STARTING_PAGE_INDEX
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeysForFirstItem(state)
                val prevKey = remoteKeys?.prevKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)

                prevKey
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeysForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)

                nextKey
            }
        }

        return try {
            val apiResponse = communityService.getMembers(page)
            val users = apiResponse.response

            val endOfPaginationReached = users.size < NETWORK_PAGE_SIZE

            appDatabase.withTransaction {

                val prevKey = if (page == COMMUNITY_STARTING_PAGE_INDEX) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                val keys = users.map { user: UserDataSource ->
                    RemoteKeys(repoId = user.id, prevKey = prevKey, nextKey = nextKey)
                }

                appDatabase.remoteKeysDao().insertAll(keys)
                appDatabase.communityDao().addCommunityMembers(users)
            }

            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (ex: IOException) {
            MediatorResult.Error(ex)
        } catch (ex: HttpException) {
            MediatorResult.Error(ex)
        }
    }

    private suspend fun getRemoteKeysForLastItem(state: PagingState<Int, UserWithLike>): RemoteKeys? {
        // get the last page that was retrieved, that contained items.
        // from that last page, get the last item
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { user: UserWithLike ->
            // get the remote keys of the last item retrieved
            appDatabase.remoteKeysDao().remoteKeysRepoId(user.user.id)
        }
    }

    private suspend fun getRemoteKeysForFirstItem(state: PagingState<Int, UserWithLike>): RemoteKeys? {
        // get the first page that was retrieved, that contained items.
        // from that first page, get the first item
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()?.let { user: UserWithLike ->
            // get the remote keys for the first item retrieved
            appDatabase.remoteKeysDao().remoteKeysRepoId(user.user.id)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, UserWithLike>): RemoteKeys? {
        // the paging library is trying to load data after the anchor position
        // get the item closest to the anchor position
        return state.anchorPosition?.let { position: Int ->
            state.closestItemToPosition(position)?.user?.id?.let { repoId: Int ->
                appDatabase.remoteKeysDao().remoteKeysRepoId(repoId)
            }
        }
    }
}