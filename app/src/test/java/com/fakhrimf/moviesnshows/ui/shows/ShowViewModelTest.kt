package com.fakhrimf.moviesnshows.ui.shows

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.fakhrimf.moviesnshows.model.ShowModel
import com.fakhrimf.moviesnshows.model.ShowResponse
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
class ShowViewModelTest : TestCase() {
    private lateinit var vm: ShowViewModel
    private val model = ShowModel("1", "Android Test", "2020", "none", "none", "none")
    private val apiInterface: ApiInterface by lazy {
        ApiClient.getClient().create(ApiInterface::class.java)
    }

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        vm = ShowViewModel()
        mockkObject(model)
        MockKAnnotations.init(this)
    }

    @Test
    fun testGetShowsList() {
        val list = ArrayList<ShowModel>()
        list.add(model)
        vm.showsList.value = list
        vm.showsList.observeForever {
            assertEquals(list, it)
        }
        every { model.title } returns "Shows"
        list.removeAt(0)
        list.add(model)
        vm.showsList.observeForever {
            assertEquals(list, it)
        }
    }

    @Test
    fun testGetPopularShows() {
        val call: Call<ShowResponse> = apiInterface.getPopularShow(API_KEY, LOCALE_EN)
        call.enqueue(object : Callback<ShowResponse> {
            override fun onFailure(call: Call<ShowResponse>, t: Throwable) {
                throw t
            }

            override fun onResponse(call: Call<ShowResponse>, response: Response<ShowResponse>) {
                assertEquals(200, response.code())
            }
        })
    }

    @After
    fun afterTests() {
        unmockkAll()
    }
}