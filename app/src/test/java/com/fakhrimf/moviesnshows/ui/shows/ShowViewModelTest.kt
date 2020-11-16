package com.fakhrimf.moviesnshows.ui.shows

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.IdlingRegistry
import com.fakhrimf.moviesnshows.utils.EspressoHelper
import com.fakhrimf.moviesnshows.utils.repository.MovieShowRepository
import com.fakhrimf.moviesnshows.utils.repository.local.MovieShowDatabase
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(manifest = Config.NONE)
@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
class ShowViewModelTest : TestCase() {
    private lateinit var context: Context
    private lateinit var vm: ShowViewModel
    private lateinit var testScope: TestCoroutineScope
    private lateinit var testDispatcher: TestCoroutineDispatcher

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        testDispatcher = TestCoroutineDispatcher()
        testScope = TestCoroutineScope(testDispatcher)
        context = ApplicationProvider.getApplicationContext<Context>()
        val database = MovieShowDatabase.getDatabase(context)
        vm = ShowViewModel(MovieShowRepository(database.movieDao(), database.showDao()))
        IdlingRegistry.getInstance().register(EspressoHelper.get())
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun testGetShows() {
        vm.getPopularShows()
        testScope.launch(testDispatcher) {
            vm.allShows.observeForever {
                assertEquals(200, vm.responseCode)
                assertNotNull(it)
            }
        }
    }

    @After
    fun afterTests() {
        Dispatchers.resetMain()
        IdlingRegistry.getInstance().unregister(EspressoHelper.get())
    }
}