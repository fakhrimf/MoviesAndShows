package com.fakhrimf.moviesnshows.ui.movies

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.fakhrimf.moviesnshows.HomeActivity
import com.fakhrimf.moviesnshows.R
import com.fakhrimf.moviesnshows.utils.EspressoHelper
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MoviesFragmentTest {
    @get:Rule
    var activityRule = ActivityScenarioRule<HomeActivity>(HomeActivity::class.java)

    @Before
    fun init() {
        IdlingRegistry.getInstance().register(EspressoHelper.get())
    }

    @After
    fun afterTests() {
        IdlingRegistry.getInstance().unregister(EspressoHelper.get())
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rvMovie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                10
            )
        )
    }

    @Test
    fun loadDetails() {
        onView(withId(R.id.rvMovie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        onView(withId(R.id.title)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}