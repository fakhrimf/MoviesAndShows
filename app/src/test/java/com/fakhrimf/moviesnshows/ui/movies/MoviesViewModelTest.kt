package com.fakhrimf.moviesnshows.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.fakhrimf.moviesnshows.model.MovieModel
import com.fakhrimf.moviesnshows.model.MovieResponse
import com.fakhrimf.moviesnshows.utils.API_KEY
import com.fakhrimf.moviesnshows.utils.LOCALE_EN
import com.fakhrimf.moviesnshows.utils.source.remote.ApiClient
import com.fakhrimf.moviesnshows.utils.source.remote.ApiInterface
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

    @Before
    fun init() {
        vm = MoviesViewModel()
        mockkObject(model)
        MockKAnnotations.init(this)
    }

    @Test
    fun testGetMoviesList() {
        val list = ArrayList<MovieModel>()
        list.add(model)
        vm.moviesList.value = list
        vm.moviesList.observeForever {
            assertEquals(list, it)
        }
        every { model.title } returns "More Tests"
        list.removeAt(0)
        list.add(model)
        vm.moviesList.observeForever {
            assertEquals(list, it)
        }
    }

    @Test
    fun testGetPopularMovies() {
        val call: Call<MovieResponse> = apiInterface.getPopularMovie(API_KEY, LOCALE_EN)
        call.enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                throw t
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                assertEquals(200, response.code())
            }
        })
    }

    @After
    fun afterTests() {
        unmockkAll()
    }
}
