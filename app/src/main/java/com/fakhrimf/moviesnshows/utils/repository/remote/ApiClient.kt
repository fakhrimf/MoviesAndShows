package com.fakhrimf.moviesnshows.utils.repository.remote

import com.fakhrimf.moviesnshows.model.MovieModel
import com.fakhrimf.moviesnshows.model.MovieResponse
import com.fakhrimf.moviesnshows.model.ShowModel
import com.fakhrimf.moviesnshows.model.ShowResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class ApiClient {
    companion object {
        fun getClient(): Retrofit {
            val baseUrl = "https://api.themoviedb.org/3/"
            return Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create()).build()
        }
    }
}

interface ApiInterface {
    //For Movies
    @GET("movie/latest")
    fun getLatestMovie(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<MovieModel>

    @GET("movie/popular")
    fun getPopularMovie(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<MovieResponse>

    @GET("discover/movie")
    fun getDiscoverMovie(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("primary_release_date.gte") releaseDateGte: String,
        @Query("primary_release_date.lte") releaseDateLte: String
    ): Call<MovieResponse>

    //For Shows
    @GET("tv/latest")
    fun getLatestShow(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<ShowModel>

    @GET("tv/popular")
    fun getPopularShow(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<ShowResponse>

    //Search
    @GET("search/movie")
    fun searchMovie(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("query") query: String
    ): Call<MovieResponse>

    @GET("search/tv")
    fun searchShow(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("query") query: String
    ): Call<ShowResponse>
}
