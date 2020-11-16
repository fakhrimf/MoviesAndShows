package com.fakhrimf.moviesnshows.utils.repository

import android.app.Application
import com.fakhrimf.moviesnshows.utils.repository.local.MovieShowDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MovieShowApplication: Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy {
        MovieShowDatabase.getDatabase(this)
    }
    val repository by lazy {
        MovieShowRepository(database.movieDao(), database.showDao())
    }
}