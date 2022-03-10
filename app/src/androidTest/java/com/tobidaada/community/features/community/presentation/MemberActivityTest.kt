package com.tobidaada.community.features.community.presentation

import androidx.lifecycle.Lifecycle
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.google.common.truth.Truth.assertThat
import com.tobidaada.community.R
import com.tobidaada.community.features.community.data.datasource.local.AppDatabase
import com.tobidaada.community.features.community.domain.entities.User
import com.tobidaada.community.features.community.presentation.paging.pagingadapter.MemberAdapter
import com.tobidaada.community.features.community.presentation.paging.pagingadapter.MemberViewHolder
import com.tobidaada.community.utils.NETWORK_PAGE_SIZE
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class MemberActivityTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var db: AppDatabase

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @After
    fun teardown() {
        runBlocking {
            db.clearAllTables()
        }
    }

    @Test
    fun recyclerViewDisplaysData() {
        val activityScenario = ActivityScenario.launch(MemberActivity::class.java)
        activityScenario.moveToState(Lifecycle.State.RESUMED)

        onView(withId(R.id.memberRv)).check { view, noViewFoundException ->
            if (noViewFoundException != null) {
                throw noViewFoundException
            }

            val recyclerView = view as RecyclerView

            val expectedSize = NETWORK_PAGE_SIZE

            assertThat(recyclerView.adapter?.itemCount).isEqualTo(expectedSize)
        }

        activityScenario.close()
    }
}