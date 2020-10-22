package com.fakhrimf.moviesnshows.ui.movies

import android.os.SystemClock
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.fakhrimf.moviesnshows.HomeActivity
import com.fakhrimf.moviesnshows.R
import com.fakhrimf.moviesnshows.model.MovieModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MoviesFragmentTest {
    private lateinit var moviesDummy: ArrayList<MovieModel>

    @get:Rule
    var activityRule = ActivityScenarioRule<HomeActivity>(HomeActivity::class.java)

    @Before
    fun setup() {
        moviesDummy = ArrayList()
        for (i in 0 until 10) {
            moviesDummy.add(MovieModel("$i", "$i", "$i", null, null, "$i"))
        }
    }

    @Test
    fun loadMovies() {
        SystemClock.sleep(800)
        onView(withId(R.id.rvMovie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                moviesDummy.size
            )
        )
    }

    @Test
    fun loadDetails() {
        SystemClock.sleep(800)
        onView(withId(R.id.rvMovie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                ViewActions.click()
            )
        )
        onView(withId(R.id.title)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}