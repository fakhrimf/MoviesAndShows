package com.fakhrimf.moviesnshows.ui.shows

import androidx.lifecycle.*
import com.fakhrimf.moviesnshows.model.ErrorState
import com.fakhrimf.moviesnshows.model.ShowModel
import com.fakhrimf.moviesnshows.model.ShowResponse
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

class ShowViewModel(private val repository: MovieShowRepository) : ViewModel() {
    val allShows: LiveData<List<ShowModel>> by lazy {
        repository.allShows
    }
    val errorState: MutableLiveData<ErrorState> by lazy {
        MutableLiveData<ErrorState>()
    }
    private val apiInterface: ApiInterface by lazy {
        ApiClient.getClient().create(ApiInterface::class.java)
    }

    fun insertShows(showModel: ShowModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertShows(showModel)
        }
    }

    fun getPopularShows() {
//        uncomment kode dibawah untuk testing idle resource, kode di comment agar tidak
//        memory leak.
//        EspressoHelper.increment()
        val call: Call<ShowResponse> = apiInterface.getPopularShow(API_KEY, LOCALE_EN)
        call.enqueue(object : Callback<ShowResponse> {
            override fun onFailure(call: Call<ShowResponse>, t: Throwable) {
                errorState.value = ErrorState(true, t.message)
            }

            override fun onResponse(call: Call<ShowResponse>, response: Response<ShowResponse>) {
//                if(!EspressoHelper.get().isIdleNow) EspressoHelper.decrement()
                for (i in response.body()!!.results) insertShows(i)
            }
        })
    }
}

class ShowViewModelFactory(private val repository: MovieShowRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShowViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ShowViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}