package com.fakhrimf.moviesnshows.ui.movies

import androidx.lifecycle.*
import com.fakhrimf.moviesnshows.model.ErrorState
import com.fakhrimf.moviesnshows.model.MovieModel
import com.fakhrimf.moviesnshows.model.MovieResponse
import com.fakhrimf.moviesnshows.utils.API_KEY
import com.fakhrimf.moviesnshows.utils.EspressoHelper
import com.fakhrimf.moviesnshows.utils.LOCALE_EN
import com.fakhrimf.moviesnshows.utils.repository.MovieShowRepository
import com.fakhrimf.moviesnshows.utils.repository.remote.ApiClient
import com.fakhrimf.moviesnshows.utils.repository.remote.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesViewModel(private val repository: MovieShowRepository) : ViewModel() {
    val allMovies: LiveData<List<MovieModel>> by lazy {
        repository.allMovies
    }
    var responseCode = 0
    val errorState: MutableLiveData<ErrorState> by lazy {
        MutableLiveData<ErrorState>()
    }
    private val apiInterface: ApiInterface by lazy {
        ApiClient.getClient().create(ApiInterface::class.java)
    }

    private fun insertMovies(movieModel: MovieModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertMovies(movieModel)
        }
    }

    fun getPopularMovies() {
//        uncomment kode dibawah untuk testing idle resource, kode di comment agar tidak
//        memory leak.
        EspressoHelper.increment()
        val call: Call<MovieResponse> = apiInterface.getPopularMovie(API_KEY, LOCALE_EN)
        call.enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                errorState.value = ErrorState(true, t.message)
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (!EspressoHelper.get().isIdleNow) EspressoHelper.decrement()
                responseCode = response.code()
                for (i in response.body()!!.results) insertMovies(i)
            }
        })
    }
}

class MoviesViewModelFactory(private val repository: MovieShowRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MoviesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}