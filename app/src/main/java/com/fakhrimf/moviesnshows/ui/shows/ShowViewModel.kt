package com.fakhrimf.moviesnshows.ui.shows

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fakhrimf.moviesnshows.model.ErrorState
import com.fakhrimf.moviesnshows.model.ShowModel
import com.fakhrimf.moviesnshows.model.ShowResponse
import com.fakhrimf.moviesnshows.utils.API_KEY
import com.fakhrimf.moviesnshows.utils.LOCALE_EN
import com.fakhrimf.moviesnshows.utils.source.remote.ApiClient
import com.fakhrimf.moviesnshows.utils.source.remote.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShowViewModel : ViewModel() {
    val showsList: MutableLiveData<ArrayList<ShowModel>> by lazy {
        MutableLiveData<ArrayList<ShowModel>>()
    }
    val errorState: MutableLiveData<ErrorState> by lazy {
        MutableLiveData<ErrorState>()
    }
    private val apiInterface: ApiInterface by lazy {
        ApiClient.getClient().create(ApiInterface::class.java)
    }

    fun getPopularShows() {
        val call: Call<ShowResponse> = apiInterface.getPopularShow(API_KEY, LOCALE_EN)
        call.enqueue(object : Callback<ShowResponse> {
            override fun onFailure(call: Call<ShowResponse>, t: Throwable) {
                errorState.value = ErrorState(true, t.message)
            }

            override fun onResponse(call: Call<ShowResponse>, response: Response<ShowResponse>) {
                showsList.value = response.body()?.results
            }
        })
    }
}