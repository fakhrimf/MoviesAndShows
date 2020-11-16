package com.fakhrimf.moviesnshows.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.fakhrimf.moviesnshows.model.MovieModel
import com.fakhrimf.moviesnshows.model.MovieResponse
import com.fakhrimf.moviesnshows.utils.API_KEY
import com.fakhrimf.moviesnshows.utils.LOCALE_EN
import com.fakhrimf.moviesnshows.utils.repository.remote.ApiClient
import com.fakhrimf.moviesnshows.utils.repository.remote.ApiInterface
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockkObject
import io.mockk.unmockkAll
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@RunWith(JUnit4::class)
class MoviesViewModelTest : TestCase() {
    private lateinit var vm: MoviesViewModel
    private val model = MovieModel("1", "Android Test", "2020", "none", "none", "none")
    private val apiInterface: ApiInterface by lazy {
        ApiClient.getClient().create(ApiInterface::class.java)
    }

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @After
    fun afterTests() {
        unmockkAll()
    }
}
