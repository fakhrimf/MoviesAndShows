package com.fakhrimf.moviesnshows.utils.repository

import androidx.annotation.WorkerThread
import com.fakhrimf.moviesnshows.model.MovieModel
import com.fakhrimf.moviesnshows.model.ShowModel
import com.fakhrimf.moviesnshows.utils.repository.local.MovieDao
import com.fakhrimf.moviesnshows.utils.repository.local.ShowDao

class MovieShowRepository(private val movieDao: MovieDao, private val showDao: ShowDao) {
    val allMovies = movieDao.getAllMovies()
    val allShows = showDao.getAllShows()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertMovies(movieModel: MovieModel) {
        movieDao.insertMovies(movieModel)
    }


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertShows(showModel: ShowModel) {
        showDao.insertShows(showModel)
    }
}