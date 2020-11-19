package com.fakhrimf.moviesnshows.ui.shows

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.IdlingRegistry
import com.fakhrimf.moviesnshows.model.ShowModel
import com.fakhrimf.moviesnshows.utils.EspressoHelper
import com.fakhrimf.moviesnshows.utils.repository.MovieShowRepository
import com.fakhrimf.moviesnshows.utils.repository.local.MovieShowDatabase
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
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

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        context = ApplicationProvider.getApplicationContext<Context>()
        val database = MovieShowDatabase.getDatabase(context)
        vm = ShowViewModel(MovieShowRepository(database.movieDao(), database.showDao()))
        IdlingRegistry.getInstance().register(EspressoHelper.get())
    }

    @Test
    fun testInsert() {
        vm.insertShows(ShowModel("1", "1", "1", "1", "1", "1"))
    }

    @Test
    fun testGetShows() {
        vm.getPopularShows()
        vm.allShows.observeForever {
            assertNotNull(it)
        }
    }

    @After
    fun afterTests() {
        IdlingRegistry.getInstance().unregister(EspressoHelper.get())
    }
}