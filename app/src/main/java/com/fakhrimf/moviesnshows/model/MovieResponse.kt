package com.fakhrimf.moviesnshows.model

data class MovieResponse(
    var page:Int,
    val results : ArrayList<MovieModel>,
    val totalResult : Int,
    val totalPage : Int
)