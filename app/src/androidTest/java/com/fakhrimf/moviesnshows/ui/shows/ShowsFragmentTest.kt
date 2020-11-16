package com.fakhrimf.moviesnshows.ui.shows

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.fakhrimf.moviesnshows.HomeActivity
import com.fakhrimf.moviesnshows.R
import com.fakhrimf.moviesnshows.utils.EspressoHelper
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ShowsFragmentTest {
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

    private fun swipeToShows() {
        val matcher = allOf(withText("Shows"), isDescendantOfA(withId(R.id.tab_layout)))
        onView(matcher).perform(click())
    }

    @Test
    fun loadShows() {
        swipeToShows()
        onView(withId(R.id.rvShows)).perform(
            scrollToPosition<RecyclerView.ViewHolder>(
                10
            )
        )
    }

    @Test
    fun loadDetails() {
        swipeToShows()
        onView(withId(R.id.rvShows)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.title))
            .check(ViewAssertions.matches(isDisplayed()))
    }
}