package com.fakhrimf.moviesnshows.ui.movies

import com.fakhrimf.moviesnshows.model.MovieModel

interface MoviesListener {
    fun onClick(movieModel: MovieModel)
}