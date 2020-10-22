package com.fakhrimf.moviesnshows.ui.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fakhrimf.moviesnshows.model.ErrorState
import com.fakhrimf.moviesnshows.model.MovieModel
import com.fakhrimf.moviesnshows.model.MovieResponse
import com.fakhrimf.moviesnshows.utils.API_KEY
import com.fakhrimf.moviesnshows.utils.LOCALE_EN
import com.fakhrimf.moviesnshows.utils.source.remote.ApiClient
import com.fakhrimf.moviesnshows.utils.source.remote.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesViewModel() : ViewModel() {
    val moviesList: MutableLiveData<ArrayList<MovieModel>> by lazy {
        MutableLiveData<ArrayList<MovieModel>>()
    }
    val errorState: MutableLiveData<ErrorState> by lazy {
        MutableLiveData<ErrorState>()
    }
    private val apiInterface: ApiInterface by lazy {
        ApiClient.getClient().create(ApiInterface::class.java)
    }

    fun getPopularMovies() {
        val call: Call<MovieResponse> = apiInterface.getPopularMovie(API_KEY, LOCALE_EN)
        call.enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                errorState.value = ErrorState(true, t.message)
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                moviesList.value = response.body()?.results
            }
        })
    }
}